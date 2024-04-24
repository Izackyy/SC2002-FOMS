package Entity;

import java.util.Timer;
import java.util.TimerTask;

import Enums.OrderStatus;
import Stores.Order;

public class OrderCancellation {
    private Timer timer;

    public OrderCancellation() {
        this.timer = new Timer();
    }

    public void scheduleOrderCancellation(Order order) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (order.getOrderStatus() == OrderStatus.READY_TO_PICKUP) {
                    cancelOrder(order);
                }
            }
        }, 5 ); // 5 minutes i think?
    }

    public void cancelOrder(Order order) {
        order.cancelOrder(); // Assuming Order class has a cancelOrder() method
        System.out.println("Order " + order.getOrderID() + " automatically canceled due to non-collection.");
        // Remove order from the queue or perform any other necessary actions
    }
}
