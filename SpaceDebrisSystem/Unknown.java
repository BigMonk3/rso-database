/**
 * Represents an Unknown type of space object.
 */
public class Unknown extends SpaceObject {
    private int conjunctionCount;

    public Unknown(String recordId, String satelliteName, String country, String orbitType,
                  int launchYear, String launchSite, double longitude, double avgLongitude,
                  String geohash, int daysOld, int conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite,
              longitude, avgLongitude, geohash, daysOld);
        this.conjunctionCount = conjunctionCount;
    }

    @Override
    public String getObjectType() {
        return "Unknown";
    }

    @Override
    public int getConjunctionCount() {
        return conjunctionCount;
    }
}
