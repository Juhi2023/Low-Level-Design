
import java.util.*;
import models.*;
import managers.*;
import strategies.payment.*;
import constants.*;
import services.NotificationService;

public class DeliverySystem {
    List<User> users;

    public DeliverySystem() {
        users = new ArrayList<>();
        initializeRestaurants();
    }

    public void initializeRestaurants() {
        Restaurant restaurant1 = new Restaurant(1, "Bikaner", 1, 2);
        restaurant1.addMenuItem(new MenuItem("P1", "Chole Bhature", 10));
        restaurant1.addMenuItem(new MenuItem("P2", "Samosa", 15));

        Restaurant restaurant2 = new Restaurant(2, "Haldiram", 3, 4);
        restaurant2.addMenuItem(new MenuItem("P1", "Raj Kachori", 80));
        restaurant2.addMenuItem(new MenuItem("P2", "Pav Bhaji", 100));
        restaurant2.addMenuItem(new MenuItem("P3", "Dhokla", 50));


        RestaurantManager rm = RestaurantManager.getInstance();
        rm.addRestaurant(restaurant1);
        rm.addRestaurant(restaurant2);

        DeliveryManager dm = DeliveryManager.getInstance();
        dm.addAgent(new DeliveryAgent(1, "Ram", "+2242424", 34, 67.9, false));
        dm.addAgent(new DeliveryAgent(1, "Ram", "+2242424", 34, 67.9, true));
    }


    public List<Restaurant> searchRestaurants() {
        return RestaurantManager.getInstance().getRestaurants();
    }

    public void selectRestaurant(User user, Restaurant restaurant) {
        Cart cart = user.getCart();
        cart.setRestaurant(restaurant);
    }

    public void addToCart(User user, String itemCode) {
        Restaurant restaurant = user.getCart().getRestaurant();
        if (restaurant == null) {
            System.out.println("Please select a restaurant first.");
            return;
        }
        for (MenuItem item : restaurant.getMenu()) {
            if (item.getCode().equals(itemCode)) {
                user.getCart().addItem(item);
                break;
            }
        }
    }

    public Order checkout(User user, PaymentStrategy paymentStrategy) {
        if (user.getCart().getItems().size()==0) return null;

        Order order = new Order(user, user.getCart().getRestaurant(), user.getCart().getItems(), paymentStrategy);

        OrderManager.getInstance().placeOrder(order);
        return order;
    }

}
