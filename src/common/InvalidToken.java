package common;

public class InvalidToken extends Exception {
    /**
     * Indicates invalid token attempt
     * @param token invalid token used
     */
    public InvalidToken(Integer token) {
        super(String.format("Invalid token %s", token.toString()));
    }
}
