package models;

import factory.ProductFactory;
import java.util.*;
import strategy.*;

public class Warehouse{
    static int i=0;
    int id;
    String name;
    double x;
    double y;

    private Map<Integer, Product> products;
    private Map<Integer,Integer> stock;   

    ReplenishStrategy replenishStrategy;  

    public Warehouse(String name, double x, double y){
        this.id = ++i;
        this.name= name;
        this.x = x;
        this.y = y;
        products = new HashMap<>();
        stock = new HashMap<>();
    }

    

    public double distanceTo(double ux, double uy) {
        return Math.sqrt((x - ux)*(x - ux) + (y - uy)*(y - uy));
    }

    public void runReplenishment(Map<Integer,Integer> itemsToReplenish) {
        if (replenishStrategy != null) {
            replenishStrategy.replenish(this, itemsToReplenish);
        }
    }


    //warehouse operation
    public void addProduct(Product prod, int quantity){
        int sku = prod.getSku();
        if (!products.containsKey(sku)) {
            products.put(sku, prod);
        }
        // else drop the extra prod instance
        stock.put(sku, stock.getOrDefault(sku, 0) + quantity);
    }

    public Product getProduct(int sku){
        return products.get(sku);
    }
    
    public void removeProduct(int sku, int quantity){
        if (!stock.containsKey(sku)) 
            return;

        int currentQuantity   = stock.get(sku);
        int remainingQuantity = currentQuantity - quantity;
        if (remainingQuantity > 0) {
            stock.put(sku, remainingQuantity);
        } else {
            stock.remove(sku);
            products.remove(sku);
        }
    }
    
    public int checkStock(int sku){
        return stock.getOrDefault(sku, 0);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }


    // Getters & Setters
    public void setReplenishStrategy(ReplenishStrategy strategy) {
        this.replenishStrategy = strategy;
    }

    public String getName() {
        return this.name;
    }

    public double getXCoordinate() {
        return this.x;
    }

    public double getYCoordinate() {
        return this.y;
    }

    

}