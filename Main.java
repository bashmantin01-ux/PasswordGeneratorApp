public class Main {

    public static void main(String[] args) {
        try {
            //Generate a 12-character password with all character requirements (uppercase, lowercase, digit, special char)
            String password1 = PasswordGenerator.generatePassword(12, true, true, true, true);
            System.out.println("Generated password 1: " + password1);

            //Generate an 8-character password with letters and digits only (no special chars)
            String password2 = PasswordGenerator.generatePassword(8, true, true, true, false);
            System.out.println("Generated password 2: " + password2);

            //Generate a 16-character password with letters and special chars only (no digits)
            String password3 = PasswordGenerator.generatePassword(16, true, true, false, true);
            System.out.println("Generated password 3: " + password3);

            //Attempting to generate a password with an invalid length (should throw exception)
            String password4 = PasswordGenerator.generatePassword(25, true, true, true, true);
            System.out.println("Generated password 4: " + password4);
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}