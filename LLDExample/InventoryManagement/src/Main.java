import models.*;
import manager.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1) Initialize.
        InventoryManagemenrtSystem.initialize();

        // 2) A User comes on Platform
        User user = new User("Aditya", 1.0, 1.0);
        System.out.println("\nUser with name " + user.name + " comes on platform");

        // 3) Show all available items via Zepto
        List<Product> products = InventoryManagemenrtSystem.showAllItems(user);
        for(Product p: products){
            System.out.println(p.getName()+ " "+p.getPrice());
        }

        // 4) User adds items to cart
        System.out.println("\nAdding items to cart");
        Cart cart = user.getCart();
        cart.addItem(products.get(0), 3);
        cart.addItem(products.get(1), 3);
        cart.addItem(products.get(2), 2);

        // 5) Place Order
        OrderManager.getInstance().placeOrder(user);

        System.out.println("\n=== Demo Complete ===");
    }
}