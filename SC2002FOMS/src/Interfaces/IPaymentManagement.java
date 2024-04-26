package Interfaces;

import java.io.IOException;

public interface IPaymentManagement {

    void addPaymentMethod() throws IOException;

    void removePaymentMethod() throws IOException;

    void editPayment() throws IOException;
}
/**
 * The {@code IPaymentManagement} interface provides methods for managing payment methods.
 * It includes functionality to add, remove, and edit payment methods, all of which may throw {@code IOException}.
 */
