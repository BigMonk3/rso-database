/*
 * Scientist class
 */
import java.util.HashMap;

public class Scientist extends User {
    
    public Scientist(String userId, String name, String role) {
        super(userId, name, role);
    }

    public HashMap<String, String[]> getMenuOptions() {
        HashMap<String, String[]> options = new HashMap<String, String[]>();

        String[] objTypes = {"Rocket Body", "Debris", "Payload", "Unknown"};
        options.put("Track Objects in Space", objTypes);

        String[] statusOptions = {"Track Objects in LEO", "Assess if Debris is Still in Orbit"};
        options.put("Assess Orbit Status", statusOptions);
        
        options.put("Back", null);

        return options;
    } 
}
