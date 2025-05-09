import java.util.Scanner;

/**
 * main operations of the system
 * such as user type selection and login
 */
public class MissionControl {

    /**
     * starts simulation and handles user interaction
     */
    public void startSimulation() {
        Scanner scanner = new Scanner(System.in); 
        boolean running = true;

        while (running) {
            System.out.println("\n=== Space Debris Management System ===");
            System.out.println("Select User Type:");
            System.out.println("1. Scientist");
            System.out.println("2. Space Agency Representative");
            System.out.println("3. Policy Maker");
            System.out.println("4. Administrator");
            System.out.println("5. Exit");

            // InputHelper for safe input
            int choice = InputHelper.getIntInput(scanner, "Enter an Option (1-5): ", 1, 5);

            if (choice == 5) {
                Logger.logExit();
                System.out.println("Exiting program.");
                break;
            }

            System.out.print("Enter username: ");
            String username = scanner.nextLine().toLowerCase();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = UserManager.authenticateUser(username, password);

            if (user == null) {
                System.out.println("Invalid username or password. Try again.");
                continue;
            }

            boolean allowed = switch (choice) {
                case 1 -> user instanceof ScientistUser;
                case 2 -> user instanceof SpaceAgencyUser;
                case 3 -> user instanceof PolicymakerUser;
                case 4 -> user instanceof AdminUser;
                default -> false;
            };

            if (!allowed) {
                System.out.println("Access denied. You do not have permission for this role.");
                Logger.log("Failed login: Role mismatch for user: " + username);
                continue;
            }

            Logger.log("User: " + username + " logged in as " + user.role);
            user.displayMenu(scanner);
        }
        scanner.close();
    }
}
