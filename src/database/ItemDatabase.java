package database;

import common.DatabaseError;
import common.ItemDoesNotExist;
import common.ItemExists;

import java.util.List;
import java.util.ArrayList;

public class ItemDatabase {
    private final List<Item> db;
    /**
     * Database with items
     */
    public ItemDatabase() {
        db = new ArrayList<>();
    }

    /**
     * Add item to database, zero initial quantity
     * @param name item name
     * @param description item description
     * @param type item type
     * @param price item price
     * @throws ItemExists item name exists in database
     */
    public void add(String name, String description, String type, Double price) throws ItemExists {
        if (db.stream().anyMatch(item -> item.getName().equals(name))) {
            throw new ItemExists(name);
        }

        db.add(new Item(name, description, type, price, 0));
    }

    public void remove(String item) throws ItemDoesNotExist {

    }

    public void updateItemDescription(String item, String description) throws ItemDoesNotExist {

    }

    public void updateItemPrice(String item, Double price) throws ItemDoesNotExist {

    }

    public void updateItemQuantity(String item, Integer quantity) throws ItemDoesNotExist {

    }

    /**
     * Execute DatabaseCommand on database
     * @param command command to execute
     * @throws DatabaseError database error
     */
    public void acceptCommand(DatabaseCommand command) throws DatabaseError {
        command.execute(this);
    }
}
