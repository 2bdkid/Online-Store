package common;

public class DatabaseError extends Exception {
    /**
     * Indicated database error
     * @param error error message
     */
    public DatabaseError(String error) {
        super(error);
    }
}
