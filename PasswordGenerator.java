import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

    // Character sets
    private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";

    private static final String ALL_CHARACTERS = UPPER_CASE_LETTERS + LOWER_CASE_LETTERS + DIGITS + SPECIAL_CHARS;

    // Secure random object for better security
    private static final SecureRandom random = new SecureRandom();

    // Method to generate a password with the given length
    public static String generatePassword(int length, boolean requireUpperCase, boolean requireLowerCase, boolean requireDigit, boolean requireSpecialChar) {

        // Validate password length
        if (length < 8 || length > 20) {
            throw new IllegalArgumentException("Password length must be between 8 and 20 characters.");
        }

        // List to store the password characters
        List<Character> passwordChars = new ArrayList<>();

        // Ensure the password contains at least one of each required character type
        if (requireUpperCase) {
            passwordChars.add(randomChar(UPPER_CASE_LETTERS));
        }
        if (requireLowerCase) {
            passwordChars.add(randomChar(LOWER_CASE_LETTERS));
        }
        if (requireDigit) {
            passwordChars.add(randomChar(DIGITS));
        }
        if (requireSpecialChar) {
            passwordChars.add(randomChar(SPECIAL_CHARS));
        }

        // Fill in the rest of the password with random characters from the combined character set
        while (passwordChars.size() < length) {
            passwordChars.add(randomChar(ALL_CHARACTERS));
        }

        // Shuffle the list to randomize the position of required characters
        java.util.Collections.shuffle(passwordChars);

        // Build the password string from the list of characters
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

    // Method to generate a random character from a given set
    private static char randomChar(String charSet) {
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }
}