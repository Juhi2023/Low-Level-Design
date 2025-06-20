package burgertypes;

public class BasicWheatBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Wheat Burger with bun, patty, and ketchup!");
    }
}