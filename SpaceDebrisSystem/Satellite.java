public class Satellite extends SpaceObject { 

    private String satelliteType;

    public Satellite (String recordID, String satelliteName, String country,
        String orbitType, String launchSite, String geohash,
        int launchYear, int daysOld, Double longitude,
        Double avgLongitude, String satelliteType) {
        
        super(recordID, satelliteName, country, orbitType, launchSite, geohash, launchYear, daysOld,
            longitude, avgLongitude);
        this.satelliteType = satelliteType;
    }

    public String transmitData() {
        return satelliteType;
    }

    @Override
    public boolean isInOrbit(){
        return orbitType != null && !orbitType.equalsIgnoreCase("Unknown") && longitude != 0.0;
    }

    @Override
    public String getRiskLevel(){
        return "N/A";
    }
}