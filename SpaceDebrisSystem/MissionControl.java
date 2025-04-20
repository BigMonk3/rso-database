/*
 * controls main operations of the system
 * such as user type selection
 */
import java.util.Scanner;

public class MissionControl {
  /*
   * Starts simulation and handles main user interaction
   */

  public void startSimulation(){
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    //create one user of each type since we don't have a user management system yet
    Scientist scientist = new Scientist();
    SpaceAgencyRep spaceRep = new SpaceAgencyRep();
    Policymaker policymaker = new Policymaker();
    Administrator admin = new Administrator();

    while(running){
      System.out.println("\n=== Space Debris Management System ===");
      System.out.println("Select User Type:");
      System.out.println("1. Scientist");
      System.out.println("2. Space Agency Representative");
      System.out.println("3. Policy Maker");
      System.out.println("4. Administrator");
      System.out.println("5. Exit");
      System.out.print("Enter an Option: ");

      char choice = scanner.next().charAt(0);
      scanner.nextLine(); //makes new line

      switch(choice){
        case '1':
          System.out.print("\033[H\033[2J");
          scientist.display(scanner);
          break;
        case '2':
          System.out.print("\033[H\033[2J");
          spaceRep.display(scanner);
          break;
        case '3':
          System.out.print("\033[H\033[2J");
          policymaker.display(scanner);
          break;
        case '4':
          System.out.print("\033[H\033[2J");
          admin.display(scanner);
          break;
        case '5':
          running = false;
          System.out.println("Exiting Program");
          break;
        default:
          System.out.print("\033[H\033[2J");
          System.out.println("Invalid Option. Please try again.");
      }
    }
    scanner.close();
  }
}
