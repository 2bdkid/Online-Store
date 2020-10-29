package common;

public class UsernameExists extends Exception {
    /**
     * Indicates username already exists
     * @param username the username that already exists
     */
    public UsernameExists(String username) {
        super(String.format("Username %s already exists", username));
    }
}
