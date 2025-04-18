/*
 * manage user roles, menu, and actions
 */

import java.util.Scanner;

public class MissionControl {

  public User[] users;

  public void clear() {
    System.out.print("\033[H\033[2J");
  }

  public void start(){
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

    }
    input.close();  
  }
}
