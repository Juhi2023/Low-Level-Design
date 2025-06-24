package models;
import java.util.*;

public class Cart{
    private List<MenuItem> items;
    private Restaurant restaurant;

    public Cart() {
        restaurant = null;
        items = new ArrayList<>();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

     public List<MenuItem> getItems() {
        return items;
    }

    public void setRestaurant(Restaurant r) {
        restaurant = r;
    }

    public void addItem(MenuItem item) {
        if (restaurant == null) {
            System.err.println("Cart: Set a restaurant before adding items.");
            return;
        }
        items.add(item);
    }
    
    public double getTotalCost() {
        double sum = 0;
        for (MenuItem it : items) {
            sum += it.getPrice();
        }
        return sum;
    }

    public void clear() {
        items.clear();
        restaurant = null;
    }
}