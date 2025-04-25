/*
 * Controls main operations of the system
 * such as user type selection
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class MissionControl {

  /**
   * Starts simulation and handles main user interaction
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
      System.out.print("Enter an Option: ");

      int choice = -1;

      // Handle invalid non-integer input
      try {
          choice = scanner.nextInt();
          scanner.nextLine(); // consume newline
      } catch (InputMismatchException e) {
          System.out.println("Invalid input. Please enter a number (1-5).");
          scanner.nextLine(); // clear the invalid input
          continue;
      }

      if (choice == 5) {
        Logger.logExit();
        System.out.println("Exiting program.");
        break;
      }

      if (choice < 1 || choice > 5) {
        System.out.println("Invalid option. Please enter a number between 1 and 5.");
        continue;
      }

      System.out.print("Enter username: ");
      String username = scanner.nextLine().toLowerCase().trim();

      System.out.print("Enter password: ");
      String password = scanner.nextLine().trim();

      User user = UserManager.authenticateUser(username, password);

      if (user == null) {
        System.out.println("Invalid username or password. Try again.");
        Logger.log("Failed login attempt with username: " + username);
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
        Logger.log("Failed role access attempt by user: " + username);
        continue;
      }

      Logger.log("User: " + username + " logged in as " + user.role);
      user.displayMenu(scanner);
    }

    scanner.close();
  }
}
