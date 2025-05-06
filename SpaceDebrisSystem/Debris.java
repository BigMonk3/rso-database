/**
 * represents a debris object
 */
public class Debris extends SpaceObject {
    private int conjunctionCount;

    public Debris(String recordId, String satelliteName, String country, String orbitType,
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
        return "Debris";
    }

    
    /** 
     * @return int
     */
    @Override
    public int getConjunctionCount() {
        return conjunctionCount;
    }
}
