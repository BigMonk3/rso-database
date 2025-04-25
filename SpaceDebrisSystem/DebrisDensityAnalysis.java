import java.util.*;
import java.util.Scanner;

/**
 * Class responsible for analyzing and generating density reports
 * based on the longitudes of space objects.
 */
public class DebrisDensityAnalysis {

    /**
     * Prompts the user for a longitude range and generates a report
     * of all objects whose longitudes fall within the specified range.
     * The report includes the count of such objects and their key details.
     */
    public static void generateDensityReports() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter lower bound of longitude: ");
            int lowerBound = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("Enter upper bound of longitude: ");
            int upperBound = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("\n--- Debris Density Report ---");
            System.out.printf("%-10s %-20s %-10s %-10s %-10s %-15s\n",
                    "Record ID", "Satellite Name", "Country", "Orbit", "Year", "Object Type");

            long count = SpaceObjectData.loadObjects().values().stream()
                    .filter(o -> o.getLongitude() >= lowerBound && o.getLongitude() <= upperBound)
                    .peek(o -> System.out.printf("%-10s %-20s %-10s %-10s %-10d %-15s\n",
                            o.getRecordId(), o.getSatelliteName(), o.getCountry(),
                            o.getOrbitType(), o.getLaunchYear(), o.getObjectType()))
                    .count();

            System.out.println("\nTotal objects in range: " + count);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numeric values.");
            scanner.nextLine(); // clear bad input
        }
    } 
}