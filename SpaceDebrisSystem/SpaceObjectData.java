import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Handles loading of space object data from a CSV file into a HashMap.
 */
public class SpaceObjectData {

    public static HashMap<String, SpaceObject> loadObjects(String csvFilePath) {
        HashMap<String, SpaceObject> objectMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }

                String[] values = line.split(",");

                // Assuming the columns are in a fixed order:
                String recordId = values[0].trim();
                String satelliteName = values[1].trim();
                String country = values[2].trim();
                String orbitType = values[3].trim();
                int launchYear = Integer.parseInt(values[4].trim());
                String launchSite = values[5].trim();
                double longitude = Double.parseDouble(values[6].trim());
                double avgLongitude = Double.parseDouble(values[7].trim());
                String geohash = values[8].trim();
                int daysOld = Integer.parseInt(values[9].trim());
                String objectType = values[10].trim(); // e.g., Payload, Rocket Body, Debris, Unknown

                SpaceObject obj;

                if (objectType.equalsIgnoreCase("Debris")) {
                    obj = new Debris(recordId, satelliteName, country, orbitType, launchYear,
                                     launchSite, longitude, avgLongitude, geohash, daysOld);
                } else {
                    obj = new Satellite(recordId, satelliteName, country, orbitType, launchYear,
                                        launchSite, longitude, avgLongitude, geohash, daysOld, objectType);
                }

                objectMap.put(recordId, obj);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return objectMap;
    }
}
