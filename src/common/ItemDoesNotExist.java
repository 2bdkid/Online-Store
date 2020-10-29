package common;

public class ItemDoesNotExist extends Exception {
    public ItemDoesNotExist(String item) {
        super(String.format("Item %s does not exist", item));
    }
}
