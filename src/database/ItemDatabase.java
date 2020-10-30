package database;

import common.DatabaseError;
import common.ItemDoesNotExist;
import common.ItemExists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class ItemDatabase {
    private final List<Item> db;
    /**
     * Database with items
     * Reads items from itemdatabase.csv
     */
    public ItemDatabase() {
        db = new ArrayList<>();
        InputStream itemDatabaseStream = getClass().getResourceAsStream("/itemdatabase.csv");

        if (itemDatabaseStream != null) {
            BufferedReader databaseReader = new BufferedReader(new InputStreamReader(itemDatabaseStream));

            String line;
            while (true) {
                try {
                    if ((line = databaseReader.readLine()) == null) break;
                    String[] data = line.split(",");
                    Double price = Double.parseDouble(data[3]);
                    Integer quantity = Integer.parseInt(data[4]);
                    db.add(new Item(data[0], data[1], data[2], price, quantity));
                } catch (IOException ignored) {

                }
            }
        }
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

    /**
     * Remove item from database
     * @param name name of item to remove
     * @throws ItemDoesNotExist item does not exist
     */
    public void remove(String name) throws ItemDoesNotExist {
        if (!db.removeIf(item -> item.getName().equals(name))) {
            throw new ItemDoesNotExist(name);
        }
    }

    /**
     * Update item description
     * @param name item to change
     * @param description new description
     * @throws ItemDoesNotExist item does not exist
     */
    public void updateItemDescription(String name, String description) throws ItemDoesNotExist {
        Item dbItem = db.stream()
                        .filter(item -> item.getName().equals(name))
                        .findFirst()
                        .orElseThrow(() -> new ItemDoesNotExist(name));

        dbItem.setDescription(description);
    }

    /**
     * Change item price
     * @param name item to change
     * @param price new price
     * @throws ItemDoesNotExist item does not exist
     */
    public void updateItemPrice(String name, Double price) throws ItemDoesNotExist {
        Item dbItem = db.stream()
                        .filter(item -> item.getName().equals(name))
                        .findFirst()
                        .orElseThrow(() -> new ItemDoesNotExist(name));

        dbItem.setPrice(price);
    }

    /**
     * Change item quantity
     * @param name item to change
     * @param quantity new quantity
     * @throws ItemDoesNotExist item does not exist
     */
    public void updateItemQuantity(String name, Integer quantity) throws ItemDoesNotExist {
        Item dbItem = db.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ItemDoesNotExist(name));

        dbItem.setQuantity(quantity);
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
