package models;

import java.util.*;
import strategies.payment.*;
import constants.*;

public class Order {
    private static int nextOrderId = 0;

    private int orderId;
    private User user;
    private Restaurant restaurant;
    private List<MenuItem> items;
    private PaymentStrategy paymentStrategy;
    private double total;
    private OrderStatus status;

    public Order(User user, Restaurant restaurant, List<MenuItem> items, PaymentStrategy paymentStrategy) {
        this.user = user;
        this.restaurant = restaurant;
        this.paymentStrategy = paymentStrategy;
        for(MenuItem m: items){
            this.total += m.getPrice();
        }
        this.orderId = ++nextOrderId;
        this.status= OrderStatus.PENDING;
    }

    public boolean processPayment() {
        if (paymentStrategy != null) {
            paymentStrategy.pay(total);
            this.status = OrderStatus.CONFIRMED;
            return true;
        } else {
            System.out.println("Please choose a payment mode first");
            return false;
        }
    }


    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setPaymentStrategy(PaymentStrategy p) {
        paymentStrategy = p;
    }


    public double getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus  status) {
        this.status = status;
    }

}
