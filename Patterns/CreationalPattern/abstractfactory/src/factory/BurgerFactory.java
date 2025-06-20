package factory;

import burgertypes.BasicBurger;
import burgertypes.StandardBurger;
import burgertypes.PremiumBurger;
import burgertypes.Burger;
import garlicbreadtypes.GarlicBread;
import garlicbreadtypes.StandardGarlicBread;
import garlicbreadtypes.BasicGarlicBread;
import garlicbreadtypes.PremiumGarlicBread;

class BurgerFactory implements BurgerFactoryInterface{
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }

    public GarlicBread createGarlicBread(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicGarlicBread();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardGarlicBread();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumGarlicBread();
        } else {
            System.out.println("Invalid garlic bread type!");
            return null;
        }
    }
}