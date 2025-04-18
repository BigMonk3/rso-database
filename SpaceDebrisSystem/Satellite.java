/**
 * Represents a satellite or space object with a defined type like Payload or Rocket Body.
 */
public class Satellite extends SpaceObject {
    private String objectType;

    public Satellite(String recordId, String satelliteName, String country, String orbitType,
                     int launchYear, String launchSite, double longitude,
                     double avgLongitude, String geohash, int daysOld, String objectType) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld);
        this.objectType = objectType;
    }

    public String getObjectType() {
        return objectType;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: " + objectType;
    }
}
