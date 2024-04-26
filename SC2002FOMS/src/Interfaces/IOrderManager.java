package Interfaces;

import java.io.IOException;

public interface IOrderManager {
    void displayNewOrder(String branch) throws IOException;
    void processOrder() throws IOException;
    void viewDetails() throws IOException;
}
