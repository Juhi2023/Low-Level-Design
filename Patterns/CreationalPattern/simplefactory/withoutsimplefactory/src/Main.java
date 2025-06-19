import burgertypes.Burger;
import burgertypes.BasicBurger;
import burgertypes.StandardBurger;
import burgertypes.PremiumBurger;

public class Main {
    public static void main(String[] args) {
        String type = "standard";
        Burger burger = null;

        if (type.equalsIgnoreCase("basic")) {
            burger = new BasicBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            burger = new StandardBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            burger = new PremiumBurger();
        } else {
            System.out.println("Invalid burger type!");
            burger = null;
        }



        if (burger != null) {
            burger.prepare();
        }
    }
}