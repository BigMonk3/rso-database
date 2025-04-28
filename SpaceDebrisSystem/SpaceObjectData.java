import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * handles loading of space object data from CSV file into a HashMap
 * reads from a CSV file and maps each object type
 * to its appropriate subclass Debris, Payload, RocketBody, Unknown
 */
public class SpaceObjectData {

    // Default path to the CSV file                 
    private static final String DEFAULT_CSV_PATH = "data/rso_metrics.csv";

    /**
     * loads objects using the default path of CSV file
     * @return HashMap mapping record IDs to SpaceObject instances
     */
    public static HashMap<String, SpaceObject> loadObjects() {
        return loadObjects(DEFAULT_CSV_PATH);
    }

    /**
     * reads CSV file and parses it into a HashMap of SpaceObject
     * identifying object type selecting appropriate subclass
     * @param csvFilePath file path to the space object CSV file
     * @return HashMap<String, SpaceObject> of parsed data
     */
    public static HashMap<String, SpaceObject> loadObjects(String csvFilePath) {
        
        HashMap<String, SpaceObject> objectMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            boolean isFirstLine = true;

            while (line != null) {
                if (isFirstLine) {
                    line = br.readLine();
                    // skip header row
                    isFirstLine = false; 
                    continue;
                }

                String[] values = line.split(",");
                // skip header row
                if (values.length < 20) continue; 

                // extract fields in expected CSV order based on project prompt
                String recordId = values[0].trim();
                String satelliteName = values[2].trim();
                String country = values[3].trim();
                String orbitType = values[4].trim();
                String objectType = values[5].trim().toLowerCase();
                int launchYear = Integer.parseInt(values[6].trim());
                String launchSite = values[7].trim();
                double longitude = parseDoubleSafe(values[8].trim());
                double avgLongitude = parseDoubleSafe(values[9].trim());
                String geohash = values[10].trim();
                int daysOld = Integer.parseInt(values[19].trim());
                int conjunctionCount = Integer.parseInt(values[20].trim());

                SpaceObject obj;

                // match CSV object type to corresponding Java class
                switch (objectType) {
                    case "debris" -> obj = new Debris(recordId, satelliteName, country, orbitType, launchYear,
                            launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);

                    case "payload" -> obj = new Payload(recordId, satelliteName, country, orbitType, launchYear,
                            launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);

                    case "rocket body", "rocketbody" -> obj = new RocketBody(recordId, satelliteName, country, orbitType, launchYear,
                            launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);

                    case "unknown", "" -> obj = new Unknown(recordId, satelliteName, country, orbitType, launchYear,
                            launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);

                    default -> obj = new Payload(recordId, satelliteName, country, orbitType, launchYear,
                            launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                }

                // store in map by record ID
                objectMap.put(recordId, obj);
                line = br.readLine();
            }

            Logger.log("Loaded " + objectMap.size() + " space objects from file: " + csvFilePath);

        } catch (IOException e) {
            Logger.log("Error reading file: " + e.getMessage());
            System.err.println("Error reading file: " + e.getMessage());
        }

        return objectMap;
    }

    /**
     * helper function to safely parse doubles defaulting to 0 if invalid
     * @param s input string representing a double
     * @return parsed double value or 0.0 on error
     */
    private static double parseDoubleSafe(String s) {
        if (s == null || s.isEmpty()) return 0.0;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

