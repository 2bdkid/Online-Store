package database;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private String description;
    private final String type;
    private Double price;
    private Integer quantity;

    /**
     * Item with attributes
     * @param name item name
     * @param description item description
     * @param type item type
     * @param price item price
     */
    public Item(String name, String description, String type, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Get item name
     * @return item name
     */
    public String getName() {
        return name;
    }

    /**
     * Get item description
     * @return item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get item type
     * @return item type
     */
    public String getType() {
        return type;
    }

    /**
     * Get item price
     * @return item price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Get item quantity
     * @return item quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Set item name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set item description
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set item price
     * @param price new price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Set item quantity
     * @param quantity new quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * String representation of Item
     * @return string representation
     */
    public String toString() {
        return String.format("%s (%s) %s | %f", name, type, description, price);
    }
}
