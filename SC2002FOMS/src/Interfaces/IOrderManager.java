package Interfaces;

import java.io.IOException;
/**
 * The {@code IOrderManager} interface outlines the operations necessary for managing orders.
 * This includes displaying new orders for a specific branch, processing orders, and viewing order details,
 * with all methods potentially throwing {@code IOException} due to I/O operations.
 */
public interface IOrderManager {
    void displayNewOrder(String branch) throws IOException;
    void processOrder(String branch) throws IOException;
    void viewDetails() throws IOException;
}
