package Entity;

import Stores.MenuItem;

/**
 * Represents an item in a shopping cart.
 */
public class CartItem {
    
    private MenuItem item;
    private int quantity;
    private double price;
    private String customisation;

    /**
     * Constructor for creating a cart item.
     * @param menuItem The menu item.
     * @param quantity The quantity of the item.
     * @param customisation Customisation options for the item.
     */
    public CartItem(MenuItem menuItem, int quantity, String customisation) {
        this.item = menuItem;
        this.quantity = quantity;
        this.price = menuItem.getPrice();
        this.customisation = customisation;
    }

    /**
     * Sets the quantity of the cart item.
     * @param quantity The new quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the item.
     * @return The item.
     */
    public MenuItem getItem() {
        return item;
    }

    /**
     * Gets the quantity of the item.
     * @return The quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the price of the item.
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the customisation of the item.
     * @return The customisation.
     */
    public String getCustomisation() {
        return customisation;
    }
}
