import java.util.Scanner;

/**
 * Concrete implementation of a Scientist user.
 * Handles all menu interactions for Scientists.
 */
public class ScientistUser extends User {

    public ScientistUser(String username, String password, String firstName, String lastName, String dateOfBirth) {
        super(username, password, firstName, lastName, dateOfBirth, UserRole.SCIENTIST);
    }

    @Override
    public void displayMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Scientist Menu ===");
            System.out.println("1. Track Object in Space");
            System.out.println("2. Assess Orbit Status");
            System.out.println("3. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    Logger.log("Scientist selected 'Track Object in Space'.");
                    TrackingSystem.trackObjects(scanner);
                }
                case 2 -> {
                    Logger.log("Scientist selected 'Assess Orbit Status'.");
                    TrackingSystem.assessOrbitStatus(scanner);
                }
                case 3 -> {
                    Logger.log("Scientist returned to main menu.");
                    back = true;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
