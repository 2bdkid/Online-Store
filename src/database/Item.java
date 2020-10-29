package database;

public class Item {
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

    public String toString() {
        return String.format("%s (%s) %s | %f", name, type, description, price);
    }
}
