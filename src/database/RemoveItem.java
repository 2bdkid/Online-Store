package database;

import common.ItemDatabase;
import common.ItemDoesNotExist;

import java.rmi.RemoteException;

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
    public void execute(ItemDatabase database) throws ItemDoesNotExist, RemoteException {
        database.remove(item);
    }
}
