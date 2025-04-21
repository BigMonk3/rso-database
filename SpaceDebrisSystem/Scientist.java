/*
 * Scientist class
 */
import java.util.Scanner;

public class Scientist{

    
    /** 
     * menu for scientist
     * @param scanner
     */
    public static void display(Scanner scanner){
        boolean back = false;

        while(!back){
            System.out.println("\n=== Scientist Menu ===");
            System.out.println("1. Track Object in Space");
            System.out.println("2. Assess Orbit Status");
            System.out.println("3. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); //consume newline

            switch(choice){
                case 1:
                    TrackingSystem.trackObjects(scanner);
                    break;
                case 2:
                    TrackingSystem.assessOrbitStatus(scanner);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}