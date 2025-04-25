import java.util.Scanner;

public class AdminUser extends User {
    
    public AdminUser(String username, String password, String firstName, String lastName, String dateOfBirth) {
        super(username, password, firstName, lastName, dateOfBirth, UserRole.ADMINISTRATOR);
    }

    /**
     * displays Administrator specific menu with options to create, manage, or delete users
     * @param scanner scanner to read user input
     */
    @Override
    public void displayMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Administrator Menu ===");
            System.out.println("1. Create User");
            System.out.println("2. Manage User");
            System.out.println("3. Delete User");
            System.out.println("4. Back");

            // safe input with validation
            int choice = InputHelper.getIntInput(scanner, "Enter an option (1-4): ", 1, 4);

            switch (choice) {
                case 1 -> {
                    Logger.log("Administrator selected 'Create User'.");
                    UserManager.createUser(scanner);
                }
                case 2 -> {
                    Logger.log("Administrator selected 'Manage User'.");
                    UserManager.manageUser(scanner);
                }
                case 3 -> {
                    Logger.log("Administrator selected 'Delete User'.");
                    UserManager.deleteUser(scanner);
                }
                case 4 -> {
                    Logger.log("Administrator returned to main menu.");
                    back = true;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
