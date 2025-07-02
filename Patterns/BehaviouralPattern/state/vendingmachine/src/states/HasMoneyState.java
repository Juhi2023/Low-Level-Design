package states;

import vendingmachine.VendingMachine;
import vendingmachine.Item;

public class HasMoneyState implements VendingState{

    public VendingState insertCoin(VendingMachine machine, int coin) {
        machine.setInsertedCoin(coin);
        System.out.println("Coin inserted. Current balance: Rs " + machine.getBalance());
        return machine.getHasMoneyState();
    }

    public VendingState selectItem(VendingMachine machine, String code) {
        Item i = machine.getInventory().getItem(code);
        if (!machine.getInventory().isAvailable(code)) {

            if (machine.getInventory().isFullyEmpty()) {
                return machine.getOutOfStockState();
            }
            System.out.println(i.getName() + " is out of stock. Please select another item.");
            return machine.getHasMoneyState(); 
        }

        int enteredAmount = machine.getEnteredAmount();
        int price =  machine.getInventory().getItem(code).getPrice();

        if(enteredAmount<price){
            System.out.println("Insufficient Amount. Please enter Rs. " + (price-enteredAmount) + " More.");
            return machine.getHasMoneyState();
        }
        machine.setSelectedItem(i);
        System.out.println("Item selected.");
        return machine.getDispenseState();
    }
    
    public VendingState dispense(VendingMachine machine) {
        System.out.println("Please select item first!");
        return machine.getHasMoneyState();
    }
    
    public VendingState refund(VendingMachine machine) {
        System.out.println("Returning coin...");
        machine.returnCoins();
        return machine.getNoCoinState();
    }

    public VendingState refill(VendingMachine machine, Item i, int quantity) {
        System.out.println("Items refilling");
        machine.addItem(i, quantity);
        return machine.getHasMoneyState();
    }
}