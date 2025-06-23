public abstract class CoffeeDecorator{
    Coffee c;

    public CoffeeDecorator(Coffee c){
        this.c = c;
    }


    abstract public String getDescription();
    abstract public double getCost();
}