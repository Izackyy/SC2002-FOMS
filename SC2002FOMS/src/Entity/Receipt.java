package Entity;

/**
 * This class handles the generation of receipts for orders.
 */
public class Receipt {
    
    /**
     * Prints the receipt for a specific order.
     * @param order The order for which to print the receipt.
     */
    public static void printReceipt(NewOrder order) {
        System.out.println("============Receipt============");
        System.out.println("Thank you for ordering at " + order.getBranch().getName());
        System.out.println("");
        
        System.out.println("Dining option: " + order.getDiningOption());
        System.out.println("");
        
        order.getCart().printCart();
        
        System.out.println("Total: " + order.getCart().calculateCart()); // need to find a way to round to 2dp
    }
}
