/**
 * Represents a piece of space debris with additional data: orbit status,
 * conjunction count, and risk analysis based on orbital drift.
 */
public class Debris extends SpaceObject {

    private int conjunctionCount;
    private boolean isInOrbit;

    public Debris(String recordId, String satelliteName, String country, String orbitType,
                  int launchYear, String launchSite, double longitude,
                  double avgLongitude, String geohash, int daysOld) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld);

        this.conjunctionCount = 0;
        this.isInOrbit = true;
    }

    /**
     * Calculates the risk level of the debris based on orbital drift.
     * @return "High", "Moderate", or "Low"
     */
    public String getRiskLevel() {
        double drift = Math.abs(longitude - avgLongitude);
        if (drift > 50) return "High";
        else if (drift > 10) return "Moderate";
        else return "Low";
    }

    /**
     * Gets the conjunction count.
     * @return number of recent conjunctions
     */
    public int getConjunctionCount() {
        return conjunctionCount;
    }

    /**
     * Sets the number of recent conjunctions this debris has experienced.
     * @param conjunctionCount the count to set
     */
    public void setConjunctionCount(int conjunctionCount) {
        this.conjunctionCount = conjunctionCount;
    }

    /**
     * Returns whether the debris is still in orbit.
     * @return true if in orbit, false if exited
     */
    public boolean isInOrbit() {
        return isInOrbit;
    }

    /**
     * Sets whether the debris is still in orbit.
     * @param inOrbit boolean value to indicate orbit status
     */
    public void setInOrbit(boolean inOrbit) {
        this.isInOrbit = inOrbit;
    }

    @Override
    public String toString() {
        return super.toString() +
            String.format(" | Conjunctions: %d | Orbit Status: %s | Risk: %s",
                conjunctionCount, isInOrbit ? "In Orbit" : "Exited", getRiskLevel());
    }
}
