package database;

import common.DatabaseError;
import common.ItemDoesNotExist;
import common.ItemExists;

public class ItemDatabase {

    public void add(String item, String description, String type, Double price) throws ItemExists {

    }

    public void remove(String item) throws ItemDoesNotExist {

    }

    public void updateItemDescription(String item, String description) throws ItemDoesNotExist {

    }

    public void updateItemPrice(String item, Double price) throws ItemDoesNotExist {

    }

    public void updateItemQuantity(String item, Integer quantity) throws ItemDoesNotExist {

    }

    public void acceptCommand(DatabaseCommand command) throws DatabaseError {
        command.execute(this);
    }
}
