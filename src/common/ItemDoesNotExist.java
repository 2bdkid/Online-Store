package common;

public class ItemDoesNotExist extends DatabaseError {
    /**
     * Indicates database item does not exist
     * @param item item that does not exist
     */
    public ItemDoesNotExist(String item) {
        super(String.format("Item %s does not exist", item));
    }
}
