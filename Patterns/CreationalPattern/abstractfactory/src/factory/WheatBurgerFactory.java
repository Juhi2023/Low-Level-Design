package factory;

import burgertypes.BasicWheatBurger;
import burgertypes.StandardWheatBurger;
import burgertypes.PremiumWheatBurger;
import burgertypes.Burger;

import garlicbreadtypes.GarlicBread;
import garlicbreadtypes.StandardWheatGarlicBread;
import garlicbreadtypes.BasicWheatGarlicBread;
import garlicbreadtypes.PremiumWheatGarlicBread;

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

    public GarlicBread createGarlicBread(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatGarlicBread();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardWheatGarlicBread();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumWheatGarlicBread();
        } else {
            System.out.println("Invalid garlic bread type!");
            return null;
        }
    }
}