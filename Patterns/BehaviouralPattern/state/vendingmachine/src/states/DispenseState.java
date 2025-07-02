package states;

import vendingmachine.VendingMachine;
import vendingmachine.Item;

public class DispenseState implements VendingState{

    public VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Already dispensing item. Please wait.");
        return machine.getDispenseState();
    }

    public VendingState selectItem(VendingMachine machine, String code) {
        System.out.println("Already dispensing item. Please wait.");
        return machine.getDispenseState();
    }
    
    public VendingState dispense(VendingMachine machine) {
        Item i = machine.getSelectedItem();
        System.out.println("Dispensing item.... "+ i.getName());
        machine.getInventory().removeItem(i.getCode());

        int enteredAmount = machine.getEnteredAmount();
        int price =  machine.getSelectedItem().getPrice();
        if(enteredAmount>price){
            machine.getChange(enteredAmount-price);
        }
        return machine.getNoCoinState();
    }
    
    public VendingState refund(VendingMachine machine) {
        System.out.println("Cannot return coin while dispensing item!");
        return machine.getDispenseState();
    }

    public VendingState refill(VendingMachine machine, Item i, int quantity) {
        System.out.println("Can't refill in this state");
        return machine.getDispenseState(); 
    }
}