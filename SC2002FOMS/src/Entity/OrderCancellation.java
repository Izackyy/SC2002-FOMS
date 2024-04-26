package Entity;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Enums.OrderStatus;
import Stores.Order;
import Stores.OrderTextDB;

/**
 * Handles the cancellation of orders.
 */
public class OrderCancellation {
    private Timer timer;
    private static OrderStatus os = null;

    public OrderCancellation() {
        this.timer = new Timer();
    }

	/**
     * Schedules the cancellation of an order.
     * @param orderID The ID of the order to cancel.
     * @param branch The branch where the order was made.
     * @throws IOException If an input/output error occurs.
     */

    public void scheduleOrderCancellation(int orderID, String branch) throws IOException {
    	
    	
    	@SuppressWarnings("unchecked")
		List<Order> al = OrderTextDB.readOrder("order.txt");
		
	    for (Order order : al)
	    {
	    	if (order.getOrderID() == orderID)
	    	{
	    		os = order.getOrderStatus();
	    	}
	    }
    	
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (os == OrderStatus.READY_TO_PICKUP) {
                    try {
						cancelOrder(orderID, branch);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        }, 30000*2); //5 * 60 * 1000); // parameter here is mil sec
    }

    public void cancelOrder(int orderID, String branch) throws IOException {
        
    	@SuppressWarnings("unchecked")
		List<Order> al = OrderTextDB.readOrder("order.txt");
    	
    	Order oldOrder=null;
    	
    	for (Order order : al)
	    {
	    	if (order.getOrderID() == orderID)
	    	{
	    		oldOrder = order;
	    		break;
	    	}
	    }
    	
    	Order newOrder = new Order(orderID, branch, OrderStatus.CANCELLED);
	    
	    OrderTextDB.updateOrder("order.txt", oldOrder, newOrder);	
    	
        // can check if successful by looking at pickup list?
        // Remove order from the queue or perform any other necessary actions
    }
}
