/**
 * factory class to create User instances based on UserRole.
 */
public class UserFactory {

    public static User createUser(String username, String password, String firstName, String lastName, String dateOfBirth, UserRole role) {
        return switch (role) {
            case SCIENTIST -> new ScientistUser(username, password, firstName, lastName, dateOfBirth);
            case SPACEAGENCYREP -> new SpaceAgencyUser(username, password, firstName, lastName, dateOfBirth);
            case POLICYMAKER -> new PolicymakerUser(username, password, firstName, lastName, dateOfBirth);
            case ADMINISTRATOR -> new AdminUser(username, password, firstName, lastName, dateOfBirth);
        };
    }
}
