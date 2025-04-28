import java.util.regex.Pattern;

/*
 * Class for password validation
 */
public class PasswordValidator{

/**
* validates password to ensure it contains at least one special character and one digit
* @param password the password string to check
* @return true if valid false otherwise
*/
    public static boolean isValid(String password){
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*()_+=|<>?{}\\[\\]~-]");
        Pattern digitPattern = Pattern.compile("\\d");

        boolean hasSpecial = specialCharPattern.matcher(password).find();
        boolean hasDigit = digitPattern.matcher(password).find();

        return hasSpecial && hasDigit;
    }
}