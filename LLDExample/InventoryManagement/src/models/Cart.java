package models;

import util.Pair;
import java.util.*;

public class Cart {
    public List<Pair> items = new ArrayList<>();

    public void addItem(Product p, int qty) {
        items.add(new Pair(p, qty));
    }

    public double getTotal() {
        double sum = 0.0;
        for (Pair it : items) {
            sum += (it.getKey().getPrice() * it.getValue());
        }
        return sum;
    }

    public List<Pair> getItems() {
        return items;
    }
}
