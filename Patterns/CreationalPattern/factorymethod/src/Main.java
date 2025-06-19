import burgertypes.Burger;
import factory.WheatBurgerFactory;
import factory.BurgerFactoryInterface;

public class Main {
    public static void main(String[] args) {
        String type = "premium";

        BurgerFactoryInterface myFactory = new WheatBurgerFactory();
        Burger burger = myFactory.createBurger(type);

        if (burger != null) {
            burger.prepare();
        }
    }
}

//javac -d out src/*.java src/burgertypes/*.java  src/factory/*.java
// java -cp out Main