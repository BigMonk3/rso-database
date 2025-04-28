/**
 * represents a Rocket Body object in space
 */
public class RocketBody extends SpaceObject {
    private int conjunctionCount;

    public RocketBody(String recordId, String satelliteName, String country, String orbitType,
                  int launchYear, String launchSite, double longitude, double avgLongitude,
                  String geohash, int daysOld, int conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite,
              longitude, avgLongitude, geohash, daysOld);
        this.conjunctionCount = conjunctionCount;
    }

    
    /** 
     * @return string
     */
    @Override
    public String getObjectType() {
        return "RocketBody";
    }

    
    /** 
     * @return int
     */
    @Override
    public int getConjunctionCount() {
        return conjunctionCount;
    }
}
