public class Satellite extends SpaceObject { 

    private String satelliteType;

    public Satellite (String recordID, String satelliteName, String country,
        String orbitType, String launchSite, String geohash,
        int launchYear, int daysOld, float longitude,
        float avgLongitude, String satelliteType) {
        
        super(recordID,
            satelliteName,
            country,
            orbitType,
            launchSite,
            geohash,
            launchYear,
            daysOld,
            longitude,
            avgLongitude);
        this.satelliteType = satelliteType;
    }

    public void transmitData() {
        System.out.println("OBAMA");
    }
}
