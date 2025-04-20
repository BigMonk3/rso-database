import java.util.Scanner;

/*
 * class handles Policymaker menu options
 */
public class Administrator {

    public static void display(Scanner scanner){
        boolean back = false;

        while(!back){
            System.out.println("\n=== Administrator Menu ===");
            System.out.println("1. Create User");
            System.out.println("2. Manage User");
            System.out.println("3. Delete User");
            System.out.println("4 Back");

            char choice = scanner.next().charAt(0);
            scanner.nextLine(); //consumes newline

            switch(choice){
                case 1:
                    User.createUser();
                    break;
                case 2:
                    User.manageUser();
                    break;
                case 3:
                    User.deleteUser();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid Choice. Try Again");
            }
        }
    }


    /* 
    public Administrator(String userId, String name, String role) {
        super(userId, name, role);
    }


    public HashMap<String, String[]> getMenuOptions() {
        HashMap<String, String[]> options = new HashMap<String, String[]>();
        
        options.put("Create User", null);
        options.put("Manage User", null);
        options.put("Delete User", null);
        options.put("Back", null);

        return options;
    }
    */
}
