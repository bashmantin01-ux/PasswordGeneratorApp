import java.security.SecureRandom;

/*
 * BACKEND CLASS
 * Handles password generation logic only
 */
public class PasswordService {

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(
            int length,
            boolean upper,
            boolean lower,
            boolean numbers,
            boolean symbols) {

        String characters = "";

        if (lower) characters += "abcdefghijklmnopqrstuvwxyz";
        if (upper) characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (numbers) characters += "0123456789";
        if (symbols) characters += "!@#$%^&*()_+";

        if (characters.isEmpty()) return "";

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(
                    characters.charAt(random.nextInt(characters.length()))
            );
        }

        return password.toString();
    }

    public static String checkStrength(String password) {
        if (password.length() < 6) return "Weak";
        if (password.length() < 10) return "Medium";
        return "Strong";
    }
}
