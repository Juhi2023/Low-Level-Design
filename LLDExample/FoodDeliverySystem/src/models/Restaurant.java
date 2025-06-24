package models;
import java.util.*;
import services.NotificationService;
import constants.*;

public class Restaurant{
    private int id;
    private String name;
    private Menu menu;
    
    private double lat;
    private double lon;

    public Restaurant(int id, String name, int lat, int lon) {
        this.id = id;
        this.name= name;
        this.lat = lat;
        this.lon = lon;
        this.menu = new Menu();
    }

     public int getId() {
        return id;
    }

    public void addMenuItem(MenuItem i){
        menu.add(i);
    }

    public List<MenuItem> getMenu(){
        return menu.getMenu();
    }

    public void prepareOrder(Order order){
        order.setStatus(OrderStatus.PREPARING);
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        order.setStatus(OrderStatus.READY);

        NotificationService.notify(order.getUser().getId(), "Order is ready! Order ID: "+ order.getOrderId());

    }
}