import java.util.Scanner;

/**
 * class handles menu options for policymakers
 */
class PolicymakerUser extends User {

    public PolicymakerUser(String username, String password, String firstName, String lastName, String dateOfBirth) {
        super(username, password, firstName, lastName, dateOfBirth, UserRole.POLICYMAKER);
    }

    /**
     * displays the menu for policymakers
     * @param scanner Scanner object for user input
     */
    @Override
    public void displayMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Policymaker Menu ===");
            System.out.println("1. Review Reports on Debris Impact");
            System.out.println("2. Assess Risk Levels for Future Space Missions");
            System.out.println("3. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    Logger.log("Policymaker selected 'Review Reports on Debris Impact'.");
                    System.out.println("(Placeholder) Reviewing reports on debris impact...");
                }
                case 2 -> {
                    Logger.log("Policymaker selected 'Assess Risk Levels'.");
                    System.out.println("(Placeholder) Assessing risk levels for future missions...");
                }
                case 3 -> {
                    Logger.log("Policymaker returned to main menu.");
                    back = true;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
