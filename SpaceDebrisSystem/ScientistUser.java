import java.util.InputMismatchException;
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

            int choice = getIntInput(scanner, "Enter an option (1-3): ", 1, 3);

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
            }
        }
    }

    /**
     * Helper method to safely get integer input within a specific range.
     * Keeps prompting the user until valid input is entered.
     *
     * @param scanner Scanner for input
     * @param prompt Message to display
     * @param min Minimum valid value
     * @param max Maximum valid value
     * @return Validated integer input
     */
    private int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int input = -1;
        while (true) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }
}
