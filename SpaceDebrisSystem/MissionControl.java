/*
 * controls main operations of the system
 * such as user type selection
 */
import java.util.Scanner;

public class MissionControl {
/*
 * Starts simulation and handles main user interaction
 */

public static void main(String[] args) {
  MissionControl control = new MissionControl();
  control.startSimulation();
}

  public void startSimulation(){
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while(running){
      System.out.println("\n=== Space Debris Management System ===");
      System.out.println("Select User Type:");
      System.out.println("1. Scientist");
      System.out.println("2. Space Agency Representative");
      System.out.println("3. Policy Maker");
      System.out.println("4. Administrator");
      System.out.println("5. Exit");
      System.out.println("Enter an Option: ");

      int choice = scanner.nextInt();
      scanner.nextLine(); //makes new line
      
      switch(choice){
        case 1:
          Scientist.display(scanner);
          break;
        case 2:
          SpaceAgencyRep.display(scanner);
        case 3:
          Policymaker.display(scanner);
        case 4:
          Administrator.display(scanner);
        case 5:
          running = false;
          System.out.println("Exiting Program");
          break;
        default:
          System.out.println("Invalid Option. Please try again.");
      }
    }
    scanner.close();
  }
}