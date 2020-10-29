package common;

public class InvalidPassword extends Exception {
    /**
     * Indicates invalid login attempt
     * @param username username of failed login attempt
     */
    public InvalidPassword(String username) {
        super(String.format("Invalid password for user %s", username));
    }
}
