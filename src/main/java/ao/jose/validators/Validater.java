package ao.jose.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validater {
    
    public static boolean isValidEmail(String email) {
        // Regex for email validation
        // It should start with alphanumeric characters, followed by an '@' symbol,
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        
        // and then a domain name with at least one dot and a top-level domain       
        Pattern pattern = Pattern.compile(regex);
        
        // Create a matcher to check if the email matches the regex  
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String number) {
        // Regex for phone number validation on the angolan phone format
        // It should start with 9 and followed by 8 digits (total 9 digits)
        String regex = "^[9][0-9]{8}$";
        
        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(regex);
        
        // Create a matcher to check if the phone number matches the regex
        Matcher matcher = pattern.matcher(number);
        
        return matcher.matches();
    }


}
