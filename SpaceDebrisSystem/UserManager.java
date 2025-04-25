import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
    private static final String  USER_FILE = "users.csv";
    private static final HashMap<String, User> userMap = new HashMap<>();

    static{
        loadUsers();
    }

    private static void loadUsers(){
        try(BufferedReader br = new BufferedReader(new FileReader(USER_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 6){
                    String username = parts[0];
                    String password = parts[1];
                    String firstName = parts[2];
                    String lastName = parts[3];
                    String dateOfBirth = parts[4];
                    UserRole role = UserRole.valueOf(parts[5]);
                    User user = UserFactory.createUser(username, password, firstName, lastName, dateOfBirth, role);
                    userMap.put(username, user);
                }
            }
        } catch(IOException e){
            System.err.println("Warning: Could NOT load exisiting users.");
        }
    }

    public static void createUser(Scanner scanner){
        System.out.println("Enter first Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Enter username: ");
        String username = scanner.nextLine().toLowerCase();

        if(userMap.containsKey(username)){
            System.out.println("Username already exist.");
            return;
        }

        String password;
        while(true){
            System.out.println("Enter password (must include at least one number and one special character): ");
            password = scanner.nextLine();
            if(PasswordValidator.isValid(password)) break;
            System.out.println("Invalid password, try again.");
        }

        System.out.println("Select Role: ");
        System.out.println("1. Scientist\n2. Space Agency Representative\n3. Policymaker\n4. Administrator");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        UserRole role;
        switch (roleChoice) {
            case 1 -> role = UserRole.SCIENTIST;
            case 2 -> role = UserRole.SPACEAGENCYREP;
            case 3 -> role = UserRole.POLICYMAKER;
            case 4 -> role = UserRole.ADMINISTRATOR;
            default -> {
                System.out.println("Invalid role. User not created.");
                Logger.log("Invalid role entered during user creation.");
                return;
            }
        }
        

        User newUser = UserFactory.createUser(username, password, firstName, lastName, dateOfBirth, role);
        userMap.put(username, newUser);

        try (FileWriter fw = new FileWriter(USER_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.printf("%s,%s,%s,%s,%s,%s\n",
                username, password, firstName, lastName, dateOfBirth, role.name());

            Logger.log("User created: " + username + " as " + role.name());
            System.out.println("User created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing user file: " + e.getMessage());
        }
    }

/**
 * Allows administrator to update a user's username and/or password.
 * Prompts the admin and updates the userMap and CSV file accordingly.
 *
 * @param scanner Scanner for input
 */
public static void manageUser(Scanner scanner) {
    System.out.print("Enter username to manage: ");
    String username = scanner.nextLine().toLowerCase(); // Normalize for case-insensitivity

    if (!userMap.containsKey(username)) {
        System.out.println("User not found.");
        return;
    }

    User user = userMap.get(username);

    System.out.print("Enter new username (or press Enter to keep current): ");
    String newUsername = scanner.nextLine().trim().toLowerCase();

    System.out.print("Enter new password (or press Enter to keep current): ");
    String newPassword = scanner.nextLine();

    boolean updated = false;

    // Update password
    if (!newPassword.isEmpty()) {
        user.password = newPassword;
        Logger.log("Administrator updated password for user: " + username);
        updated = true;
    }

    // Update username
    if (!newUsername.isEmpty() && !newUsername.equals(username)) {
        user.username = newUsername;
        userMap.remove(username);
        userMap.put(newUsername, user);
        Logger.log("Administrator changed username from '" + username + "' to '" + newUsername + "'");
        updated = true;
    }

    if (updated) {
        saveUsersToFile();
        System.out.println("User updated successfully.");
    } else {
        System.out.println("No changes were made.");
    }
}

/**
 * Allows administrator to delete a user by username.
 *
 * @param scanner Scanner for input
 */
public static void deleteUser(Scanner scanner) {
    System.out.print("Enter username to delete: ");
    String username = scanner.nextLine().toLowerCase(); // Normalize for case-insensitivity

    if (userMap.remove(username) != null) {
        saveUsersToFile();
        Logger.log("Administrator deleted user: " + username);
        System.out.println("User deleted successfully.");
    } else {
        System.out.println("User not found.");
    }
}


    private static void saveUsersToFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))){
            for(User user : userMap.values()){
                writer.printf("%s,%s,%s,%s,%s,%s\n",
                    user.username, user.password, user.firstName, user.lastName, user.dateOfBirth, user.role.name());
            }
        }catch (IOException e){
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public static User authenticateUser(String username, String password){
        User user = userMap.get(username);
        if(user != null && user.password.equals(password)){
            return user;
        }
        return null;
    }
}
