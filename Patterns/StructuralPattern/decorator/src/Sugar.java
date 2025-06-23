public class Sugar extends CoffeeDecorator{
    public Sugar(Coffee c){
        super(c);
    }

    public String getDescription(){
        return c.getDescription() + " + Sugar";
    }

    public double getCost(){
        return c.getCost() + 0.5;
    }
}