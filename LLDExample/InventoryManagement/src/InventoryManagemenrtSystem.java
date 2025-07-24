import java.util.*;
import models.*;
import manager.*;
import strategy.*;
import enums.*;
import factory.*;

public class InventoryManagemenrtSystem{
    static InventoryManager imManager = InventoryManager.getInstance();

    public static List<Product> showAllItems(User user) {
        System.out.println("\n[Zepto] All Available products within 5 KM for " + user.name + ":");
        List<Warehouse> nearbyStores = imManager.getNearbyWarehouses(user.x, user.y, 5.0);
        Map<Integer,Product> products = new HashMap<>();

        for (Warehouse ds : nearbyStores) {
            for (Product product : ds.getAllProducts()) {
                int sku = product.getSku();
                if (!products.containsKey(sku)) {
                    products.put(sku, product);
                }
            }
        }

        return new ArrayList<>(products.values());
    }

     public static void initialize() {
        Product p1 = ProductFactory.createProduct(ProductType.GROCERY, 101, "Apple", 5);
        Product p2 = ProductFactory.createProduct(ProductType.GROCERY, 102, "Orange", 6);
        Product p3 = ProductFactory.createProduct(ProductType.GROCERY, 101, "Apple", 4);
        Product p4 = ProductFactory.createProduct(ProductType.GROCERY, 103, "Mango", 5);

        // Warehouse A.......
        Warehouse warehouseA = new Warehouse("warehouseA", 0.0, 0.0);
        warehouseA.setReplenishStrategy(new ThresholdReplenishStrategy(3));
        System.out.println("\nAdding stocks in warehouseA....");
        warehouseA.addProduct(p1, 3);
        warehouseA.addProduct(p2, 5);
        warehouseA.addProduct(p4, 3);

        // Warehouse B.......
        Warehouse warehouseB = new Warehouse("warehouseB", 4.0, 1.0);
        warehouseB.setReplenishStrategy(new ThresholdReplenishStrategy(3));
        System.out.println("\nAdding stocks in warehouseB....");
        warehouseB.addProduct(p3, 2);
        warehouseB.addProduct(p4, 3);


        imManager.registerWarehouse(warehouseA);
        imManager.registerWarehouse(warehouseB);
    }

}