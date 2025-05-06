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

    // default path to the CSV file                 
    // private static final String DEFAULT_CSV_PATH = "data/rso_metrics.csv";

    /**
     * loads objects using the default path of CSV file
     * @return HashMap mapping record IDs to SpaceObject instances
     */
    public static HashMap<String, SpaceObject> loadObjects() {
        return loadObjects("data/rso_metrics_columns_jumbled.csv");
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
            String[] values = line.split(",");
            HashMap<String, Integer> headers = new HashMap<>();
            boolean isFirstLine = true;

            while (true) {

                //map datatypes to indices in values list
                if (isFirstLine) {
                    for(int i = 0 , j = 0; i < values.length; i++,j++) {
                        headers.put(values[i].trim(), j);
                        //fix because geohash data values have commas in them
                        if (values[i].trim().equals("geohash")) {
                            j++;
                        }
                    }
                    isFirstLine = false; 
                    
                    continue;
                }

                line = br.readLine();
                if (line == null) break;

                values = line.split(",");

                if (values.length < 20) continue; //check valid line

                // extract fields to variables using indices stored in hashmap
                String recordId = values[headers.getOrDefault("record_id", 0)].trim();
                String satelliteName = values[headers.getOrDefault("satellite_name", 0)].trim();
                String country = values[headers.getOrDefault("country", 0)].trim();
                String orbitType = values[headers.getOrDefault("approximate_orbit_type", 0)].trim();
                String objectType = values[headers.getOrDefault("object_type", 0)].trim().toLowerCase();
                int launchYear = Integer.parseInt(values[headers.getOrDefault("launch_year", 0)].trim());
                String launchSite = values[headers.getOrDefault("launch_site", 0)].trim();
                double longitude = parseDoubleSafe(values[headers.getOrDefault("longitude", 0)].trim());
                double avgLongitude = parseDoubleSafe(values[headers.getOrDefault("avg_longitude", 0)].trim());
                String geohash = values[headers.getOrDefault("geohash", 0)].trim() + ',' + values[headers.getOrDefault("geohash", 0)+1].trim();
                int daysOld = Integer.parseInt(values[headers.getOrDefault("days_old", 0)].trim());
                int conjunctionCount = Integer.parseInt(values[headers.getOrDefault("conjunction_count", 0)].trim());

                SpaceObject obj;

                // match CSV object type to corresponding Java class
                switch (objectType) {
                    case "debris" -> obj = new Debris(recordId, satelliteName, country, orbitType, launchYear,
                            launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);

                    case "payload" -> obj = new Payload(recordId, satelliteName, country, orbitType, launchYear,
                            launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);

                    case "rocket body", "rocketbody", "ROCKETBODY", "ROCKET BODY" -> obj = new RocketBody(recordId, satelliteName, country, orbitType, launchYear,
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
