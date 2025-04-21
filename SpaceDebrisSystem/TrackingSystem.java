import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class TrackingSystem {

    private static final String CSV_PATH = "data/space_objects.csv";

    
    /** 
     * Menu for choosing different objects
     * @param scanner
     */
    public static void trackObjects(Scanner scanner) {
        HashMap<String, SpaceObject> objects = SpaceObjectData.loadObjects(CSV_PATH);

        System.out.println("\n== Track Objects ==");
        System.out.println("1. Rocket Body");
        System.out.println("2. Debris");
        System.out.println("3. Payload");
        System.out.println("4. Unknown");
        System.out.println("5. Back");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String filterType = switch (choice) {
            case 1 -> "Rocket Body";
            case 2 -> "Debris";
            case 3 -> "Payload";
            case 4 -> "Unknown";
            default -> null;
        };

        if (filterType != null) {
            List<SpaceObject> filtered = objects.values().stream()
                    .filter(o -> o.getObjectType().equalsIgnoreCase(filterType))
                    .collect(Collectors.toList());

            printObjectList(filtered);
        }
    }

    
    /** 
     * Manu to assess orbit status of an object
     * @param scanner
     */
    public static void assessOrbitStatus(Scanner scanner) {
        HashMap<String, SpaceObject> objects = SpaceObjectData.loadObjects(CSV_PATH);

        System.out.println("\n== Assess Orbit Status ==");
        System.out.println("1. Track Objects in LEO");
        System.out.println("2. Assess Debris Orbit Status");
        System.out.println("3. Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                List<SpaceObject> inLEO = objects.values().stream()
                        .filter(o -> o.getOrbitType().equalsIgnoreCase("LEO"))
                        .collect(Collectors.toList());
                printObjectList(inLEO);
            }
            case 2 -> assessDebrisStatus(objects);
        }
    }

    
    /** 
     * Outputs TXT file for Debris orbit status
     * 
     * @param objects 
     */
    private static void assessDebrisStatus(HashMap<String, SpaceObject> objects) {
        int inOrbit = 0, exited = 0;
        List<SpaceObject> exitedDebris = new ArrayList<>();

        try (PrintWriter writer = new PrintWriter("output/assessed_debris.csv");
             PrintWriter txtOut = new PrintWriter("output/exited_debris_report.txt")) {

            writer.println("Record ID,Satellite Name,Country,Orbit Type,Launch Year,Launch Site,Longitude,Avg. Longitude, Geohash,Days Old,Still In Orbit,Risk Level");

            for (SpaceObject obj : objects.values()) {
                if (!obj.getObjectType().equalsIgnoreCase("Debris")) continue;

                boolean inOrbitFlag = obj.getOrbitType() != null &&
                        !obj.getOrbitType().equalsIgnoreCase("Unknown") &&
                        obj.getLongitude() != 0 &&
                        obj.getDaysOld() < 15000 &&
                        obj.getConjunctionCount() >= 1;

                double drift = Math.abs(obj.getLongitude() - obj.getAvgLongitude());
                String riskLevel = drift > 50 ? "High"
                        : drift > 10 ? "Moderate"
                        : "Low";

                if (inOrbitFlag) inOrbit++;
                else {
                    exited++;
                    exitedDebris.add(obj);
                }

                writer.printf("%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%b,%s\n",
                        obj.getRecordId(), obj.getSatelliteName(), obj.getCountry(),
                        obj.getOrbitType(), obj.getLaunchYear(), obj.getLaunchSite(),
                        obj.getLongitude(), obj.getAvgLongitude(), obj.getGeohash(),
                        obj.getDaysOld(), inOrbitFlag, riskLevel);
            }

            txtOut.printf("In-Orbit Debris: %d\nExited Debris: %d\n\nExited Debris Details:\n", inOrbit, exited);
            for (SpaceObject ex : exitedDebris) {
                txtOut.printf("%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d\n",
                        ex.getRecordId(), ex.getSatelliteName(), ex.getCountry(),
                        ex.getOrbitType(), ex.getLaunchYear(), ex.getLaunchSite(),
                        ex.getLongitude(), ex.getAvgLongitude(), ex.getGeohash(),
                        ex.getDaysOld());
            }

            System.out.println("Orbit status assessment complete. Output files saved in 'output/' directory.");
        } catch (IOException e) {
            System.err.println("Error writing files: " + e.getMessage());
        }
    }

    
    /** 
     * @param list of space object based on data from csv
     */
    private static void printObjectList(List<SpaceObject> list) {
        if (list.isEmpty()) {
            System.out.println("No objects found.");
            return;
        }

        for (SpaceObject o : list) {
            System.out.printf(
                    "ID: %s | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Lon: %.2f | AvgLon: %.2f | Geohash: %s | Age: %d days\n",
                    o.getRecordId(), o.getSatelliteName(), o.getCountry(), o.getOrbitType(),
                    o.getLaunchYear(), o.getLaunchSite(), o.getLongitude(),
                    o.getAvgLongitude(), o.getGeohash(), o.getDaysOld()
            );
        }
    }
}
