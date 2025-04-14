import java.util.HashMap;

/*
 * class handles Policymaker menu options
 */
public class Policymaker extends User {
    
    public Policymaker(String userId, String name, String role) {
        super(userId, name, role);
    }


    public HashMap<String, String[]> getMenuOptions() {
        HashMap<String, String[]> options = new HashMap<String, String[]>();
        
        options.put("Review Reports on Debris Impact", null);
        options.put("Assess Risk Levels for Future Space Missions", null);
        options.put("Back", null);

        return options;
    }
}
