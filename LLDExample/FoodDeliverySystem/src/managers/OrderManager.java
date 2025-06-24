package managers;

import java.util.*;
import models.*;
import services.NotificationService;
import constants.*;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private static DeliveryManager dm = DeliveryManager.getInstance();
    private static RestaurantManager rm = RestaurantManager.getInstance();
    private static OrderManager instance = null;

    private OrderManager() {
        // Private Constructor
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order placeOrder(Order order) {
        //1. payment
        boolean paymentSuccess = order.processPayment();
        if (!paymentSuccess) {
            NotificationService.notify(order.getUser().getId(), "Payment failed. Order not placed.");
            return null;
        }
        
        //2.placed order
        orders.add(order);
        order.setStatus(OrderStatus.PLACED);

        //3. notify user
        NotificationService.notify(order.getUser().getId(), "Order placed successfully! Order ID: "+ order.getOrderId());


        // In step 4 and 5 i should create a metaData class instead of passing whole Order class

        //4. restaurant prepare
        rm.prepareOrder(order);

        //5. assign delivery agent
        dm.assignPartner(order);

        order.getUser().getCart().clear();

        return order;
    }

    public void listOrders() {
        System.out.println("\n--- All Orders ---");
        for (Order order : orders) {
            System.out.println(" order for " + order.getUser().getName() + " | Total: ₹" + order.getTotal());
        }
    }
}