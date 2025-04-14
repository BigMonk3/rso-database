import java.util.HashMap;

/*
 * class handles Policymaker menu options
 */
public class Administrator extends User {
    
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
}
