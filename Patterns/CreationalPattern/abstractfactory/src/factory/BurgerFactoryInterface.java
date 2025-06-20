package factory;

import burgertypes.Burger;
import garlicbreadtypes.GarlicBread;

public interface BurgerFactoryInterface {
    Burger createBurger(String type);
    GarlicBread createGarlicBread(String type);
}
