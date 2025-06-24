import models.*;
import strategies.payment.*;
import java.util.*;

class Main{
    public static void main(String args[]){
        DeliverySystem s = new DeliverySystem();

        User u = new User(4556, "Juhi", "juhi@gmail.com", "+91-35436363", 54, 67.7);

        List<Restaurant> res = s.searchRestaurants();

        //select restaurant
        s.selectRestaurant(u, res.get(1));

        //get menu
        List<MenuItem> menu = res.get(1).getMenu();
        for(MenuItem i:menu){
            System.out.println(i.getCode() + ": " + i.getName() + " ----- " + i.getPrice());
        }

        //add item to cart
        u.getCart().addItem(menu.get(1));
        u.getCart().addItem(menu.get(2));

        //place order
        s.checkout(u, new UpiPaymentStrategy("934734"));
    }
}


//javac -d out src/services/*.java src/models/*.java src/managers/*.java src/*.java src/strategies/payment/*.java src/constants/*.java