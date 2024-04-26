package Interfaces;

import java.io.IOException;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * The {@code IPaymentManagement} interface provides methods for managing payment methods.
 * It includes functionality to add, remove, and edit payment methods, all of which may throw {@code IOException}.
 */
public interface IPaymentManagement {

    void addPaymentMethod() throws IOException;

    void removePaymentMethod() throws IOException;

    void editPayment() throws IOException;
}

