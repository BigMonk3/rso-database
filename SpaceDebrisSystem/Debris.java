/*
 * represents space debris and extends SpaceObject
 */
public class Debris extends SpaceObject {
    private int conjunctionCount;

    public Debris (String recordID, String satelliteName, String country, String orbitType, String launchSite, String geohash,
    int launchYear, int daysOld, float longitude, float avgLongitude, int conjunctionCount){
        
        super(recordID, satelliteName, country, orbitType, launchSite, geohash, launchYear, daysOld, longitude, avgLongitude);
        this.conjunctionCount = conjunctionCount;
    }

    @Override
    public boolean isInOrbit(){
        return orbitType != null && !orbitType.equalsIgnoreCase("Unknow") &&
        longitude != 0.0 && daysOld < 150000 && conjunctionCount >= 1; 
    }

    @Override
    public String getRiskLevel(){
        float drift = getOrbitalDrift();
        if(drift > 50) return "High";
        else if(drift > 10) return "Moderate";
        else return "Low";
    }

    public int getConjunctionCount(){
        return conjunctionCount;
    }
}
