package database;

import common.ItemDoesNotExist;

import java.rmi.RemoteException;

public class UpdateItemDescription extends DatabaseCommand {
    private final String item;
    private final String description;

    /**
     * Command to update item description
     * @param item item name
     * @param description new item description
     */
    public UpdateItemDescription(String item, String description) {
        this.item = item;
        this.description = description;
    }

    /**
     * Change item description in database
     * @param database database to operate on
     */
    public void execute(ItemDatabase database) throws ItemDoesNotExist, RemoteException {
        database.updateItemDescription(item, description);
    }
}
