import java.util.HashMap;

/*
 * Class that handles menu options for space agency representative
 */
public class SpaceAgencyRep extends User {
    
    public SpaceAgencyRep(String userId, String name, String role) {
        super(userId, name, role);
    }

    public HashMap<String, String[]> getMenuOptions() {
        HashMap<String, String[]> options = new HashMap<String, String[]>();
        
        options.put("Analyze Long-Term Impact", null);
        options.put("Generate Density Reports", null);
        options.put("Back", null);

        return options;
    }
}