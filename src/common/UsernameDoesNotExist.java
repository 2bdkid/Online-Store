package common;

public class UsernameDoesNotExist extends Exception {
    /**
     * Indicates username does not exist
     * @param username the username that does not exist
     */
    public UsernameDoesNotExist(String username) {
        super(String.format("The username %s does not exist", username));
    }
}
