package common;

public class ItemExists extends DatabaseError {
    /**
     * Indicates unexpected item exists in database
     * @param item unexpected item in database
     */
    public ItemExists(String item) {
        super(String.format("Item %s already exists", item));
    }
}
