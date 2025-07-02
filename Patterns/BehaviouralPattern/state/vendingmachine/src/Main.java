
import vendingmachine.Item;
import vendingmachine.VendingMachine;

class Main{
    public static void main(String args[]){
        VendingMachine m = VendingMachine.getInstance();
        Item i1 = new Item("I1", "Red Ball", 5);
        Item i2 = new Item("I2", "Green Ball", 10);
        Item i3 = new Item("I3", "Yellow Ball", 15);
        Item i4 = new Item("I4", "Blue Ball", 20);

        m.refill(i1, 5);
        m.refill(i2, 0);

        m.insertCoin(5);
        
        // m.selectItem("I2");
        m.selectItem("I1");
        m.dispense();




        m.refill(i3, 3);

    }
}