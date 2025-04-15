/*
 * manage user roles, menu, and actions
 */

import java.util.Scanner;

public class MissionControl {

  private User[] users;

  public void clear() {
    System.out.print("\033[H\033[2J");
  }

  public int start(){
    int selection = -1;

    this.clear();
    System.out.println("Welcome to the Space Debris Monitoring System");
    Scanner input = new Scanner(System.in);

    while (selection < 0 || selection > 4) {
      System.out.println("Select a user type: ");
      System.out.println("\t1. Scientist");
      System.out.println("\t2. Space Agency Representative");
      System.out.println("\t3. Policymaker");
      System.out.println("\t4. Administrator\n");
      System.out.print("\t0. Exit\n\n>>> ");

      selection = input.nextInt();

      if (selection == 0) {
        this.clear();
        System.out.println("Exited.\n");
        break;
      } else if (selection <= 4) {
        return selection;
      } else {
        System.out.println("Invalid Selection. Press any key to continue: ");
        input.next();
        continue;
      }
    }
    input.close();
  }   
}
