package states;
import vendingmachine.Item;
import vendingmachine.VendingMachine;

public class OutOfStockState implements VendingState {
    public VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Machine is sold out. Coin returned: Rs " + coin);
        return machine.getOutOfStockState();
    }
    
    public VendingState selectItem(VendingMachine machine, String code) {
        System.out.println("Machine is sold out!");
        return machine.getOutOfStockState();
    }
    
    public VendingState dispense(VendingMachine machine) {
        System.out.println("Machine is sold out!");
        return machine.getOutOfStockState();
    }
    
    public VendingState refund(VendingMachine machine) {
        System.out.println("Machine is sold out. No coin inserted.");
        return machine.getOutOfStockState();
    }

    public VendingState refill(VendingMachine machine, Item i, int quantity) {
        System.out.println("Items refilling");
        machine.addItem(i, quantity);
        return machine.getNoCoinState();
    }
}