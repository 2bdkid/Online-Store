package database;

public class ItemUnavailable extends Exception {

    public ItemUnavailable(String item) {
        super(item);
    }
}
