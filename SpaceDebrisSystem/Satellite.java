/**
 * Represents a satellite or space object with a defined type like Payload or Rocket Body.
 */
public class Satellite extends SpaceObject {
    private int conjunctionCount;

    public Satellite(String recordId, String satelliteName, String country, String orbitType,
                  int launchYear, String launchSite, double longitude, double avgLongitude,
                  String geohash, int daysOld, int conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite,
              longitude, avgLongitude, geohash, daysOld);
        this.conjunctionCount = conjunctionCount;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getObjectType() {
        return "Satellite";
    }

    
    /** 
     * @return int
     */
    @Override
    public int getConjunctionCount() {
        return conjunctionCount;
    }
}