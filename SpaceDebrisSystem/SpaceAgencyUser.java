import java.util.*;

/**
 * class handles menu options for space agency representatives
 * choices for impact analysis and debris density report generation
 */
public class SpaceAgencyUser extends User {

    public SpaceAgencyUser(String username, String password, String firstName, String lastName, String dateOfBirth) {
        super(username, password, firstName, lastName, dateOfBirth, UserRole.SPACEAGENCYREP);
    }

    /**
     * displays menu for space agency representatives
     * @param scanner Scanner object for user input
     */
    @Override
    public void displayMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Space Agency Representative Menu ===");
            System.out.println("1. Analyze Long-term Impact");
            System.out.println("2. Generate Density Reports");
            System.out.println("3. Back");

            // safe input with validation
            int choice = InputHelper.getIntInput(scanner, "Enter an option (1-4): ", 1, 4);

            switch (choice) {
                case 1 -> {
                    Logger.log("Space Agency Rep selected 'Analyze Long-term Impact'.");
                    ImpactAnalysis.analyzeLongTermImpact();
                }
                case 2 -> {
                    Logger.log("Space Agency Rep selected 'Generate Density Reports'.");
                    DebrisDensityAnalysis.generateDensityReports();
                }
                case 3 -> {
                    Logger.log("Space Agency Rep returned to main menu.");
                    back = true;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}