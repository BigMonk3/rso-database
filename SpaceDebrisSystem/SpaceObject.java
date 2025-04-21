/**
 * Abstract base class representing a general object in space.
 */
public abstract class SpaceObject {
    protected String recordId;
    protected String satelliteName;
    protected String country;
    protected String orbitType;
    protected int launchYear;
    protected String launchSite;
    protected double longitude;
    protected double avgLongitude;
    protected String geohash;
    protected int daysOld;

    public SpaceObject(String recordId, String satelliteName, String country, String orbitType,
                       int launchYear, String launchSite, double longitude,
                       double avgLongitude, String geohash, int daysOld) {
        this.recordId = recordId;
        this.satelliteName = satelliteName;
        this.country = country;
        this.orbitType = orbitType;
        this.launchYear = launchYear;
        this.launchSite = launchSite;
        this.longitude = longitude;
        this.avgLongitude = avgLongitude;
        this.geohash = geohash;
        this.daysOld = daysOld;
    }

    // Existing getters
    public String getRecordId() { return recordId; }
    public String getSatelliteName() { return satelliteName; }
    public String getCountry() { return country; }
    public String getOrbitType() { return orbitType; }
    public int getLaunchYear() { return launchYear; }
    public String getLaunchSite() { return launchSite; }
    public double getLongitude() { return longitude; }
    public double getAvgLongitude() { return avgLongitude; }
    public String getGeohash() { return geohash; }
    public int getDaysOld() { return daysOld; }

    // New abstract methods for compatibility
    public abstract String getObjectType();
    public abstract int getConjunctionCount();

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Lon: %.2f | AvgLon: %.2f | Geohash: %s | Days Old: %d",
                recordId, satelliteName, country, orbitType, launchYear, launchSite,
                longitude, avgLongitude, geohash, daysOld);
    }
}
