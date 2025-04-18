/*
 * represents a space object
 */
import java.lang.Math;

public abstract class SpaceObject {
    protected String recordID;
    protected String satelliteName;
    protected String country;
    protected String orbitType;
    protected String launchSite;
    protected String geohash;
    protected int launchYear;
    protected int daysOld;
    protected Double longitude;
    protected Double avgLongitude;

    public SpaceObject(String recordID, String satelliteName, String country, String orbitType, String launchSite, String geohash,
    int launchYear, int daysOld, Double longitude, Double avgLongitude) {
        this.recordID = recordID;
        this.satelliteName = satelliteName;
        this.country = country;
        this.orbitType = orbitType;
        this.launchSite = launchSite;
        this.geohash = geohash;
        this.launchYear = launchYear;
        this.daysOld = daysOld;
        this.longitude = longitude;
        this.avgLongitude = avgLongitude; 
    }

    public Double getOrbitalDrift(){
        return Math.abs(this.longitude - this.avgLongitude);
    }

    public abstract boolean isInOrbit();
    
    public abstract String getRiskLevel();

    @Override
    public String toString(){
        return recordID + "," +  satelliteName + "," + country + "," + orbitType + "," + launchSite + "," + geohash
        + "," + launchYear + "," + daysOld + "," + longitude + "," + avgLongitude;
    }
}
