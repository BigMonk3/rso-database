import java.util.Scanner;

/*
 * class handles Policymaker menu options
 */
public class Policymaker {

    public static void display(Scanner scanner){
        boolean back = false;
        while(!back){
            System.out.println("\n=== Policymaker Menu ===");
            System.out.println("1. Review Reports on Debris Impact");
            System.out.println("2. Assess Risk Levels for Future Space Missions");
            System.out.println("3. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); //consume newline

            switch(choice){
                case 1:
                    ImpactAnalysis.reviewDebrisImpact();
                    break;
                case 2:
                    ImpactAnalysis.assessRiskLevels();
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid Choice. Try again.");
                    
            }
        }
    }
}