import java.util.Scanner;

/**
 * abstract base class representing a generic user
 * enforces the displayMenu method to allow role-specific functionality
 */
public abstract class User {
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String dateOfBirth;
    protected UserRole role;

    public User(String username, String password, String firstName, String lastName, String dateOfBirth, UserRole role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    // enforced for each subclass to implement its own menu
    public abstract void displayMenu(Scanner scanner);
}
