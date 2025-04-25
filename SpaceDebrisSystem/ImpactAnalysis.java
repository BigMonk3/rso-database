import java.util.*;
import java.util.stream.Collectors;

/**
 * Class responsible for performing impact-related analysis
 * on space objects such as long-term conjunction impact.
 */
public class ImpactAnalysis {

    private static final String CSV_PATH = "data/rso_metrics.csv";

    /**
     * Placeholder method for reviewing debris impact.
     */
    public static void reviewDebrisImpact() {
        // Future implementation
    }

    /**
     * Placeholder method for assessing risk levels.
     */
    public static void assessRiskLevels() {
        // Future implementation
    }

    /**
     * Analyzes long-term impact by identifying space objects
     * in LEO that are older than 200 days and have at least 1 conjunction.
     * Displays the key attributes of such objects.
     */
    public static void analyzeLongTermImpact() {
        HashMap<String, SpaceObject> objects = SpaceObjectData.loadObjects(CSV_PATH);

        // Filter LEO objects that are older than 200 days and have conjunction count > 0
        List<SpaceObject> impacted = objects.values().stream()
            .filter(obj -> obj.getOrbitType().equalsIgnoreCase("LEO"))
            .filter(obj -> obj.getDaysOld() > 200 && obj.getConjunctionCount() > 0)
            .collect(Collectors.toList());

        Logger.log("Analyzing long-term impact for LEO objects older than 200 days with conjunctions.");

        if (impacted.isEmpty()) {
            System.out.println("No long-term impact objects found.");
            return;
        }

        System.out.println("\n--- Long-Term Impact Analysis ---");
        for (SpaceObject obj : impacted) {
            System.out.printf("ID: %s | Name: %s | Country: %s | Orbit: %s | Type: %s | Days Old: %d | Conjunctions: %d\n",
                obj.getRecordId(), obj.getSatelliteName(), obj.getCountry(),
                obj.getOrbitType(), obj.getObjectType(), obj.getDaysOld(), obj.getConjunctionCount());
        }
        System.out.println("----------------------------------");
    }
}