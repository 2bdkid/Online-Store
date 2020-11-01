package database;

import java.io.Serializable;

public class DatabaseCommandFactory implements Serializable {
    /**
     * Get add item command
     * @param item new item name
     * @param description new item description
     * @param type new item type
     * @param price new item price
     * @return encapsulated command
     */
    public DatabaseCommand getAddItemCommand(String item, String description, String type, Double price){
        return new AddItem(item, description, type, price);
    }

    /**
     * Get remove item command
     * @param item item to remove
     * @return encapsulated command
     */
    public DatabaseCommand getRemoveItemCommand(String item){
        return new RemoveItem(item);
    }

    /**
     * Get update item description command
     * @param item item name
     * @param description new item description
     * @return encapsulated command
     */
    public DatabaseCommand getUpdateItemDescriptionCommand(String item, String description) {
        return new UpdateItemDescription(item, description);
    }

    /**
     * Get update item price command
     * @param item item name
     * @param price new item price
     * @return encapsulated command
     */
    public DatabaseCommand getUpdateItemPriceCommand(String item, Double price) {
        return new UpdateItemPrice(item, price);
    }

    /**
     * Get update item quantity command
     * @param item item name
     * @param quantity new item quantity
     * @return encapsulated command
     */
    public DatabaseCommand getUpdateItemQuantity(String item, Integer quantity) {
        return new UpdateItemQuantity(item, quantity);
    }
}
