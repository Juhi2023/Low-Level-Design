public class Milk extends CoffeeDecorator{
    public Milk(Coffee c){
        super(c);
    }

    public String getDescription(){
        return c.getDescription() + " + Milk";
    }

    public double getCost(){
        return c.getCost() + 2.0;
    }
}