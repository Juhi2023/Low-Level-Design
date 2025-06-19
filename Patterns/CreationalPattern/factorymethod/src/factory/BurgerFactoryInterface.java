package factory;

import burgertypes.Burger;

public interface BurgerFactoryInterface {
    Burger createBurger(String type);
}
