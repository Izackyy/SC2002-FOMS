package Entity;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

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
        
        System.out.println("Total: $" + order.getCart().calculateCart()); // need to find a way to round to 2dp
    }
}
