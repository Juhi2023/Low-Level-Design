class Main{
    public static void main(String args[]){
        Coffee c = new PlainCoffee();
        System.out.println(c.getDescription() + ": Rs. "+ c.getCost());

        CoffeeDecorator mc = new Milk(c);
        System.out.println(mc.getDescription() + ": Rs. "+ mc.getCost());

        CoffeeDecorator sc = new Sugar(c);
        System.out.println(sc.getDescription() + ": Rs. "+ sc.getCost());
    }
}