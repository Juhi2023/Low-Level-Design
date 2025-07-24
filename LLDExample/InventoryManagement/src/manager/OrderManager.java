package manager;

import models.*;
import java.util.*;
import factory.*;
import util.*;

public class OrderManager{
    private static OrderManager instance;
    private List<Order> orders;

    private OrderManager() {
        orders = new ArrayList<>();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void placeOrder(User u){
        Cart cart = u.getCart();
        List<Pair> items = cart.getItems();

        List<Warehouse> nearByWarehouses = InventoryManager.getInstance().getNearbyWarehouses(u.x, u.y, 50);
        if(nearByWarehouses.size()==0){
            System.out.println("Service not available.");
            return;
        }

        Warehouse firstStore = nearByWarehouses.get(0);
        boolean allInFirst = true;
        for (Pair item: items) {
            int sku = item.getKey().getSku();
            int qty = item.getValue();
            if (firstStore.checkStock(sku) < qty) {
                allInFirst = false;
                break;
            }
        }

        Order order = new Order(u);

        if(allInFirst){
            System.out.println("  All items at: " + firstStore.getName());

            for (Pair item : items) {
                int sku = item.getKey().getSku();
                int qty = item.getValue();
                firstStore.removeProduct(sku, qty);
                order.items.add(new Pair(item.getKey(), qty));
            }

            order.totalAmount = cart.getTotal();
            order.partners.add(new DeliveryPartner("Partner1"));
            System.out.println("  Assigned Delivery Partner: Partner1");
        }else{
            // System.out.println("  Splitting order across stores...");
            // Map<Integer,Integer> allItems = new HashMap<>();
            // for (Pair item : requestedItems) {
            //     allItems.put(item.getKey().getSku(), item.getValue());
            // }

            // int partnerId = 1;
            // for (Warehouse store : nearByWarehouses) {
            //     if (allItems.isEmpty()) break;
            //     System.out.println("   Checking: " + store.getName());
            //     List<Integer> toErase = new ArrayList<>();
            //     for (Map.Entry<Integer,Integer> entry : allItems.entrySet()) {
            //         int sku = entry.getKey();
            //         int qtyNeeded = entry.getValue();
            //         int availableQty = store.checkStock(sku);
            //         if (availableQty <= 0) continue;
            //         int takenQty = Math.min(availableQty, qtyNeeded);
            //         store.removeProduct(sku, takenQty);
            //         System.out.println("     " + store.getName() + " supplies SKU " + sku + " x" + takenQty);
            //         order.items.add(new Pair(ProductFactory.createProduct(sku), takenQty));
            //         if (qtyNeeded > takenQty) {
            //             allItems.put(sku, qtyNeeded - takenQty);
            //         } else {
            //             toErase.add(sku);
            //         }
            //     }
            //     for (int sku : toErase) {
            //         allItems.remove(sku);
            //     }
            //     if (!toErase.isEmpty()) {
            //         String pname = "Partner" + partnerId++;
            //         order.partners.add(new DeliveryPartner(pname));
            //         System.out.println("     Assigned: " + pname + " for " + store.getName());
            //     }
            // }
            // if (!allItems.isEmpty()) {
            //     System.out.println("  Could not fulfill:");
            //     for (Map.Entry<Integer,Integer> entry : allItems.entrySet()) {
            //         System.out.println("    SKU " + entry.getKey() 
            //                          + " x" + entry.getValue());
            //     }
            // }
            // double sum = 0;
            // for (Pair it : order.items) {
            //     sum += it.getKey().getPrice() * it.getValue();
            // }
            // order.totalAmount = sum;
        }

        // Printing Order Summary
        System.out.println("\n[OrderManager] Order #" + order.orderId + " Summary:");
        System.out.println("  User: " + u.name + "\n  Items:");
        for (Pair item : order.items) {
            System.out.println("    SKU " + item.getKey().getSku() + " (" + item.getKey().getName() + ") x" + item.getValue() + " @ Rs. " + item.getKey().getPrice());
        }
        System.out.println("  Total: Rs." + order.totalAmount + "\n  Partners:");
        for (DeliveryPartner dp : order.partners) {
            System.out.println("    " + dp.name);
        }
        System.out.println();

        orders.add(order);
    }


    public List<Order> getOrders(){
        return orders;
    }

}