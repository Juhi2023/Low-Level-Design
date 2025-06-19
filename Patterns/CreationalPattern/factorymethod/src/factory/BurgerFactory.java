package factory;

import burgertypes.BasicBurger;
import burgertypes.StandardBurger;
import burgertypes.PremiumBurger;
import burgertypes.Burger;

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
}