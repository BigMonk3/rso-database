import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class TrackingSystem {
    // full path to the CSV file should match file path with SpaceObjectData.java class
    private static final String CSV_PATH = "data/rso_metrics.csv";

    /**
     * displays a menu to allow the Scientist to view specific types of space objects
     * @param scanner Used to capture user input
     */
    public static void trackObjects(Scanner scanner) {
        System.out.println("******CALLING loadObjects()******");
        HashMap<String, SpaceObject> objects = SpaceObjectData.loadObjects();

        System.out.println("\n== Track Objects ==");
        System.out.println("1. Rocket Body");
        System.out.println("2. Debris");
        System.out.println("3. Payload");
        System.out.println("4. Unknown");
        System.out.println("5. Back");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline after numeric input

        String filterType = switch (choice) {
            case 1 -> "rocket body";
            case 2 -> "debris";
            case 3 -> "payload";
            case 4 -> "unknown";
            default -> null;
        };

        Logger.log("Scientist accessed Track Objects menu.");

        if (filterType != null) {
            Logger.log("Scientist requested to view objects of type: " + filterType);
            // filter loaded objects by type and matching objects into a list
            List<SpaceObject> filtered = objects.values().stream()
                .filter(o -> o.getObjectType().toLowerCase().trim().equals(filterType.toLowerCase().trim()))
                .collect(Collectors.toList());

            printObjectList(filtered);
        }
    }

    /**
     * Presents the orbit assessment menu to the user
     * 1 view all objects in LEO, 2 assess if debris is still in orbit
     * @param scanner scanner to read user input
     */
    public static void assessOrbitStatus(Scanner scanner) {
        HashMap<String, SpaceObject> objects = SpaceObjectData.loadObjects(CSV_PATH);

        System.out.println("\n== Assess Orbit Status ==");
        System.out.println("1. Track Objects in LEO");
        System.out.println("2. Assess Debris Orbit Status");
        System.out.println("3. Back");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> {
                Logger.log("Scientist selected 'Track Objects in LEO'.");
                // filter and display only those objects in Low Earth Orbit
                List<SpaceObject> inLEO = objects.values().stream()
                    .filter(o -> o.getOrbitType().equalsIgnoreCase("LEO"))
                    .collect(Collectors.toList());
                printObjectList(inLEO);
            }
            case 2 -> {
                Logger.log("Scientist selected 'Assess Debris Orbit Status'.");
                assessDebrisStatus(objects);
            }
            case 3 -> Logger.log("Scientist returned to previous menu.");
        }
    }

    /**
     * determines whether each debris object is still in orbit or has exited
     * outputs the results to a CSV file with risk level and orbit status and a TXT report
     * @param objects HashMap containing all space objects
     */
    private static void assessDebrisStatus(HashMap<String, SpaceObject> objects) {
        int inOrbit = 0, exited = 0;
        List<SpaceObject> exitedDebris = new ArrayList<>();

                                    // Directory to write CSV and TXT output files
        File outputDir = new File("enter a directory where you want these files saved");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Create directory if it doesn't exist
        }

        try (
            PrintWriter writer = new PrintWriter(new File(outputDir, "assessed_debris.csv"));
            PrintWriter txtOut = new PrintWriter(new File(outputDir, "exited_debris_report.txt"))
        ) {
            // CSV header line
            writer.println("Record ID,Satellite Name,Country,Orbit Type,Launch Year,Launch Site,Longitude,Avg. Longitude, Geohash,Days Old,Still In Orbit,Risk Level");

            for (SpaceObject obj : objects.values()) {
                // only evaluate objects labeled as debris
                if (!obj.getObjectType().equalsIgnoreCase("debris")) continue;

                // determine if object is still in orbit based on criteria
                boolean inOrbitFlag = obj.getOrbitType() != null &&
                    !obj.getOrbitType().equalsIgnoreCase("Unknown") &&
                    obj.getLongitude() != 0 &&
                    obj.getDaysOld() < 15000 &&
                    obj.getConjunctionCount() >= 1;

                // calculate drift from average and determine risk level
                double drift = Math.abs(obj.getLongitude() - obj.getAvgLongitude());
                String riskLevel = drift > 50 ? "High" : (drift > 10 ? "Moderate" : "Low");

                // collect exited debris objects
                if (inOrbitFlag) inOrbit++;
                else {
                    exited++;
                    exitedDebris.add(obj);
                }

                // Output assessed object to CSV file
                writer.printf("%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%b,%s\n",
                    obj.getRecordId(), obj.getSatelliteName(), obj.getCountry(),
                    obj.getOrbitType(), obj.getLaunchYear(), obj.getLaunchSite(),
                    obj.getLongitude(), obj.getAvgLongitude(), obj.getGeohash(),
                    obj.getDaysOld(), inOrbitFlag, riskLevel);
            }

            // list of exited debris in plain text format
            txtOut.printf("In-Orbit Debris: %d\nExited Debris: %d\n\nExited Debris Details:\n", inOrbit, exited);
            for (SpaceObject ex : exitedDebris) {
                txtOut.printf("%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d\n",
                    ex.getRecordId(), ex.getSatelliteName(), ex.getCountry(),
                    ex.getOrbitType(), ex.getLaunchYear(), ex.getLaunchSite(),
                    ex.getLongitude(), ex.getAvgLongitude(), ex.getGeohash(),
                    ex.getDaysOld());
            }

            Logger.log("Debris orbit status assessed: " + inOrbit + " in orbit, " + exited + " exited.");
            Logger.log("Output written to assessed_debris.csv and exited_debris_report.txt.");
            System.out.println("Orbit status assessment complete. Output files saved in 'output/' directory.");

        } catch (IOException e) {
            System.err.println("Error writing files: " + e.getMessage());
        }
    }

    /**
     * helper method to print a formatted list of space objects
     * @param list List of space objects
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
