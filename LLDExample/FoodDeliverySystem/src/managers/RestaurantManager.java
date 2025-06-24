package managers;

import models.*;
import java.util.*;
import constants.*;
import services.NotificationService;

// Singleton
public class RestaurantManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantManager instance = null;

    private RestaurantManager() {
        // private constructor
    }

    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Restaurant getRestaurant(int id) {
        for(Restaurant x : restaurants){
            if(x.getId()==id){
                return x;
            }
        }
        return null;
    }

    public void prepareOrder(Order order) {
        Restaurant r = order.getRestaurant();

        NotificationService.notify(r.getId(), "New order! Order ID: "+ order.getOrderId());

        r.prepareOrder(order);
    }

    // public List<Restaurant> searchByLocation(String loc) {
    //     List<Restaurant> result = new ArrayList<>();
    //     loc = loc.toLowerCase();
    //     for (Restaurant r : restaurants) {
    //         String rl = r.getLocation().toLowerCase();
    //         if (rl.equals(loc)) {
    //             result.add(r);
    //         }
    //     }
    //     return result;
    // }
}