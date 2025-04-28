import java.util.*;
import java.util.Scanner;

/**
 * class responsible for analyzing and generating density report
 * based on longitudes of space objects
 */
public class DebrisDensityAnalysis {

    /**
     * ask user for a longitude range and makes a report
     * of all objects whose longitudes fall within the range
     * report includes the count of objects and their details
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

            //formatting for header density report
            System.out.println("\n--- Debris Density Report ---");
            //formatting for tabs and string printing
            System.out.printf("%-10s %-20s %-10s %-10s %-10s %-15s\n",
                    "Record ID", "Satellite Name", "Country", "Orbit", "Year", "Object Type");

            //loads space objects and filters based on longitude range entered
            
            //fetches collection of objects and stream allows for mapping/filtering 
            long count = SpaceObjectData.loadObjects().values().stream()
                    .filter(o -> o.getLongitude() >= lowerBound && o.getLongitude() <= upperBound)
                    .peek(o -> System.out.printf("%-10s %-20s %-10s %-10s %-10d %-15s\n",
                            o.getRecordId(), o.getSatelliteName(), o.getCountry(),
                            o.getOrbitType(), o.getLaunchYear(), o.getObjectType()))
                    .count();

            System.out.println("\nTotal objects in range: " + count);
        
        //error handling for invalid inputs
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numeric values.");
            scanner.nextLine(); // clear bad input
        }
    } 
}