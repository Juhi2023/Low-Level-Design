package states;

import vendingmachine.VendingMachine;
import vendingmachine.Item;

public interface VendingState{
    VendingState insertCoin(VendingMachine machine, int c);
    VendingState selectItem(VendingMachine machine, String code);
    VendingState dispense(VendingMachine machine);
    VendingState refund(VendingMachine machine);
    VendingState refill(VendingMachine machine, Item i, int quantity);
}