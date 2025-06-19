import burgertypes.Burger;

public class Main {
    public static void main(String[] args) {
        String type = "standard";

        BurgerFactory myBurgerFactory = new BurgerFactory();

        Burger burger = myBurgerFactory.createBurger(type);

        if (burger != null) {
            burger.prepare();
        }
    }
}

//javac -d out src/*.java src/burgertypes/*.java
// java -cp out Main