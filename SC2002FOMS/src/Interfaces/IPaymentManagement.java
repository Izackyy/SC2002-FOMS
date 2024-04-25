package Interfaces;

import java.io.IOException;

public interface IPaymentManagement {

    void addPaymentMethod() throws IOException;

    void removePaymentMethod() throws IOException;

    void editPayment() throws IOException;
}
