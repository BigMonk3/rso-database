import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SpaceObjectData {

    public static HashMap<String, SpaceObject> loadObjects(String csvFilePath){
        HashMap<String, SpaceObject> objectMap = new HashMap<>();
    
    try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
        String line;
        boolean isFirstLine = true;

        while((line = br.readLine()) != null){
            if(isFirstLine){
                isFirstLine = false;
                continue;
            }
            String[] values = line.split(",");

            String recordId = values[0].trim();
            String satelliteName = values[1].trim();
            String country = values[2].trim();
            String orbitType = values[3].trim();
            String launchSite = values[4].trim();
            String geohash = values[5].trim();
            int launchYear = Integer.parseInt(values[6].trim());
            int daysOld = Integer.parseInt(values[7].trim());
            double longitude = Double.parseDouble(values[8].trim());
            double avgLongitude = Double.parseDouble(values[9].trim());
            String objectType = values[10].trim();

            SpaceObject obj;

            if(objectType.equalsIgnoreCase("Debris")) {
                obj = new Debris(recordId, satelliteName, country, orbitType, 
                launchSite, geohash, launchYear, daysOld, null, null, daysOld);
            }else{
                obj = new Satellite(recordId, satelliteName, country, orbitType, 
                launchSite, geohash, launchYear, daysOld, null, null, satelliteName);
            }

            objectMap.put(recordId, obj);
        }
    } catch(IOException e) {
        System.err.println("Error Reading File: " + e.getMessage());
    }
    return objectMap;
    }
}