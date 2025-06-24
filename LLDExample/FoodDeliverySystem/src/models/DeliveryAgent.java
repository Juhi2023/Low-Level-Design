package models;

import constants.*;
import services.NotificationService;

public class DeliveryAgent{
    private int id;
    private String name;
    private String phoneno;
    private boolean available;

    private double lat;
    private double lon;


    public DeliveryAgent(int id, String name, String phoneno, double lat, double lon, boolean available) {
        this.id = id;
        this.name= name;
        this.phoneno = phoneno;
        this.lat = lat;
        this.lon = lon;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void performDelivery(Order order){
        //reach customer location
        order.setStatus(OrderStatus.DELIVERED);
        NotificationService.notify(order.getUser().getId(), "Order delivered successfully! Order ID: "+ order.getOrderId());

    }

}