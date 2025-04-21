import java.util.Scanner;
/*
 * Class that handles menu options for space agency representative
 */
public class SpaceAgencyRep {

    public static void display(Scanner scanner){
        boolean back = false;

        while(!back){
            System.out.println("\n=== Space Agency Representative Menu ---");
            System.out.println("1. Analyze Long-term Impact");
            System.out.println("2. Generate Density Reports");
            System.out.println("3. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); //consume newline

            switch(choice){
                case 1:
                    ImpactAnalysis.analyzeLongTermImpact();
                    break;
                case 2:
                    DebrisDensityAnalysis.generateDensityReports();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again");
            }
        }
    }
}
