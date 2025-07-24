package models;

import util.*;
import java.util.*;


public class Order {
    private static int nextId = 1;
    public int orderId;
    public User user;
    public List<DeliveryPartner> partners = new ArrayList<>();
    public List<Pair> items = new ArrayList<>();
    public double totalAmount;

    public Order(User u) {
        orderId = nextId++;
        user    = u;
        totalAmount = 0.0;
    }
    
}