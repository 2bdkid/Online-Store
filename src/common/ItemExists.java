package common;

public class ItemExists extends Exception {
    public ItemExists(String item) {
        super(String.format("Item %s already exists", item));
    }
}
