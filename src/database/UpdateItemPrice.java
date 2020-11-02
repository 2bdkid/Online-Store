package database;

import common.ItemDoesNotExist;

import java.rmi.RemoteException;

public class UpdateItemPrice extends DatabaseCommand {
    private final String item;
    private final Double price;

    /**
     * Command to update item price in database
     * @param item item name
     * @param price new item price
     */
    public UpdateItemPrice(String item, Double price) {
        this.item = item;
        this.price = price;
    }

    /**
     * Update item price on database
     * @param database database to operate on
     */
    public void execute(ItemDatabase database) throws ItemDoesNotExist, RemoteException {
        database.updateItemPrice(item, price);
    }
}
