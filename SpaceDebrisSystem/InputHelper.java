import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class for handling user input
 */
public class InputHelper {

    /**
     * prompts the user for an integer input within a range
     * loops until a valid integer is entered
     *
     * @param scanner scanner for user input
     * @param prompt message to display
     * @param min minimum valid number
     * @param max maximum valid number
     * @return validated integer input
     */
    public static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int input = -1;
        while (true) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }
}
