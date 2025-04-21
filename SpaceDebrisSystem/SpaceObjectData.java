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

                    String[] values = line.split(",", -1); // handle empty values

                    // Adjusted for the new field ordering
                    String recordId = values[0].trim();
                    String satelliteName = values[1].trim();
                    String country = values[2].trim();
                    String orbitType = values[3].trim();
                    int launchYear = Integer.parseInt(values[4].trim());
                    String launchSite = values[5].trim();
                    double longitude = parseDoubleSafe(values[6].trim());
                    double avgLongitude = parseDoubleSafe(values[7].trim());
                    String geohash = values[8].trim();
                    int daysOld = Integer.parseInt(values[9].trim());
                    String objectType = values[10].trim();
                    int conjunctionCount = Integer.parseInt(values[11].trim()); // new field

                    SpaceObject obj;

                    switch (objectType.toLowerCase()) {
                        case "debris":
                            obj = new Debris(recordId, satelliteName, country, orbitType, launchYear,
                                    launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                            break;
                        case "payload":
                            obj = new Payload(recordId, satelliteName, country, orbitType, launchYear,
                                    launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                            break;
                        case "rocket body":
                        case "rocketbody":
                            obj = new RocketBody(recordId, satelliteName, country, orbitType, launchYear,
                                    launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                            break;
                        case "unknown":
                            obj = new Unknown(recordId, satelliteName, country, orbitType, launchYear,
                                    launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                            break;
                        default:
                            obj = new Payload(recordId, satelliteName, country, orbitType, launchYear,
                                    launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                            break;
                    }

                    objectMap.put(recordId, obj);
                }

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }

            return objectMap;
        }

        // Helper to safely parse doubles in case of empty or malformed values
        private static double parseDoubleSafe(String s) {
            if (s == null || s.isEmpty()) return 0.0;
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
    }
