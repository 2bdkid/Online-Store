package database;

import common.ItemDoesNotExist;

public class RemoveItem extends DatabaseCommand {
    private final String item;

    /**
     * Command to remove item
     * @param item item to remove
     */
    public RemoveItem(String item) {
        this.item = item;
    }

    /**
     * Remove item from database
     * @param database database to operate on
     */
    public void execute(ItemDatabaseImpl database) throws ItemDoesNotExist {
        database.remove(item);
    }
}
