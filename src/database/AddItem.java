package database;

import common.ItemExists;

import java.rmi.RemoteException;

public class AddItem extends DatabaseCommand {
    private final String item;
    private final String description;
    private final String type;
    private final Double price;

    /**
     * Command to add item to database
     * @param item item name
     * @param description item description
     * @param type item type
     * @param price item price
     */
    public AddItem(String item, String description, String type, Double price) {
        this.item = item;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    /**
     * Add item to database
     * @param database database to operate on
     */
    public void execute(ItemDatabase database) throws ItemExists, RemoteException {
        database.add(item, description, type, price);
    }
}
