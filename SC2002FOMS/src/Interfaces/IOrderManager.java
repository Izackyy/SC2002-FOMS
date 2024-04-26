package Interfaces;

import java.io.IOException;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
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
