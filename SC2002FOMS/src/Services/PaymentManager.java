package Services;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import Interfaces.IPaymentManagement;
import Stores.Payment;
import Stores.PaymentTextDB;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * PaymentManager class allows the staff to add, remove, and edit payment methods.
 */
public class PaymentManager implements IPaymentManagement {

    private static final Scanner sc = new Scanner(System.in);
    /**
     * Adds a new payment method to the system.
     * 
     * @throws IOException if an error occurs during file operations
     */
    @Override
    public void addPaymentMethod() throws IOException {

        PaymentTextDB.printPaymentMethod("payment.txt");
        System.out.println("Name:");
        String name = sc.nextLine();

        // Check for duplicate payment method names (ignoring case)
        if (PaymentTextDB.compareName("payment.txt", name)) {
            System.out.println("Payment method already exists.");
            return;
        }

        Payment payment = new Payment(name);
        PaymentTextDB.addPaymentType("payment.txt", payment);
        System.out.println("Payment method added successfully.");
        System.out.println("Updated Payment Methods:");
        return;
    }
    /**
     * Removes an existing payment method from the system.
     * 
     * @throws IOException if an error occurs during file operations
     */
    @Override
    public void removePaymentMethod() throws IOException {

        PaymentTextDB.printPaymentMethod("payment.txt");
        System.out.println("Name:");
        String name = sc.nextLine();

        List<Payment> al = PaymentTextDB.readPaymentType("payment.txt");// test
        Payment toRemove = null;
        for (Payment payment : al) {
            if (payment.getName().equalsIgnoreCase(name)) {
                toRemove = payment;
                PaymentTextDB.removePaymentType("payment.txt", toRemove);
                System.out.println("Payment method removed successfully.");
                System.out.println("Updated Payment Methods:");
                return;
            }
        }
        if (toRemove == null) {
            System.out.println("Payment method does not exist");
        }
        return;
    }
    /**
     * Edits the payment methods by allowing the user to add or remove payment methods.
     * 
     * @throws IOException if an error occurs during file operations
     */
    @Override
    public void editPayment() throws IOException {

        int Choice;
        do {
            System.out.println("===========Payment Editor===========");
            System.out.println("|| 1) Add Payment Method          ||");
            System.out.println("|| 2) Remove Payment Method       ||");
            System.out.println("|| 3) Back                        ||");
            System.out.println("====================================");
            Choice = sc.nextInt();
            sc.nextLine(); // input buffer

            switch (Choice) {
                case (1):
                    addPaymentMethod();
                    break;
                case (2):
                    removePaymentMethod();
                    break;
                default:
                    break;
            }
        } while (Choice != 3);
    }

}
