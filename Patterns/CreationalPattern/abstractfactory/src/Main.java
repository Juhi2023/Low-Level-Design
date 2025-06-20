import burgertypes.Burger;
import factory.WheatBurgerFactory;
import factory.BurgerFactoryInterface;
import garlicbreadtypes.GarlicBread;

public class Main {
    public static void main(String[] args) {
        String type = "premium";

        BurgerFactoryInterface myFactory = new WheatBurgerFactory();
        Burger burger = myFactory.createBurger(type);
        GarlicBread gb = myFactory.createGarlicBread(type);

        if (burger != null) {
            burger.prepare();
        }

        if (gb != null) {
            gb.prepare();
        }
    }
}

//javac -d out src/*.java src/burgertypes/*.java  src/factory/*.java
// java -cp out Main