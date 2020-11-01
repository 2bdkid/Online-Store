package database;

import common.ItemDatabase;
import common.ItemDoesNotExist;

import java.rmi.RemoteException;

public class UpdateItemQuantity extends DatabaseCommand {
    private final String item;
    private final Integer quantity;

    /**
     * Command to update item quantity
     * @param item item name
     * @param quantity new item quantity
     */
    public UpdateItemQuantity(String item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Update item quantity in database
     * @param database database to operate on
     */
    public void execute(ItemDatabase database) throws ItemDoesNotExist, RemoteException {
        database.updateItemQuantity(item, quantity);
    }
}
