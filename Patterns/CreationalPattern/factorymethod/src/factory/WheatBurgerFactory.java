package factory;

import burgertypes.BasicWheatBurger;
import burgertypes.StandardWheatBurger;
import burgertypes.PremiumWheatBurger;
import burgertypes.Burger;

public class WheatBurgerFactory implements BurgerFactoryInterface {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardWheatBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumWheatBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}