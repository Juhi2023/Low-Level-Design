package states;
import vendingmachine.Item;
import vendingmachine.VendingMachine;

public class IdleState implements VendingState{

    public VendingState insertCoin(VendingMachine machine, int coin) {
        machine.setInsertedCoin(coin);
        System.out.println("Coin inserted. Current balance: Rs " + machine.getBalance());
        return machine.getHasMoneyState();
    }

    public VendingState selectItem(VendingMachine machine, String code) {
        System.out.println("Please insert coin first!");
        return machine.getNoCoinState();
    }
    
    public VendingState dispense(VendingMachine machine) {
        System.out.println("Please insert coin and select item first!");
        return machine.getNoCoinState();
    }
    
    public VendingState refund(VendingMachine machine) {
        System.out.println("No coin to return!");
        return machine.getNoCoinState();
    }

    public VendingState refill(VendingMachine machine, Item i, int quantity) {
        System.out.println("Items refilling");
        machine.addItem(i, quantity);
        return machine.getNoCoinState();
    }
}