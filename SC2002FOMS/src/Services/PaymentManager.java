package Services;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import Interfaces.IPaymentManagement;
import Stores.Payment;
import Stores.PaymentTextDB;

public class PaymentManager implements IPaymentManagement {

    private static final Scanner sc = new Scanner(System.in);

    @Override
    public void addPaymentMethod() throws IOException {

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
        PaymentTextDB.printPaymentMethod("payment.txt");
        return;
    }

    @Override
    public void removePaymentMethod() throws IOException {

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
                PaymentTextDB.printPaymentMethod("payment.txt");
                return;
            }
        }
        if (toRemove == null) {
            System.out.println("Payment method does not exist");
        }
        return;
    }

    @Override
    public void editPayment() throws IOException {

        int Choice;
        do {
            PaymentTextDB.printPaymentMethod("payment.txt");
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
