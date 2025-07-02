package vendingmachine;

import java.util.*;
import states.VendingState;
import states.IdleState;
import states.HasMoneyState;
import states.DispenseState;
import states.OutOfStockState;

public class VendingMachine{
    static VendingMachine instance;
    Inventory inventory;
    private VendingState noCoinState;
    private VendingState hasMoneyState;
    private VendingState dispenseState;
    private VendingState outOfStockState;
    private VendingState currentState;

    // private List<Coins> coins;
    private int balance;
    private int enteredAmount;

    private Item selectedItem;

    private VendingMachine(){
        inventory = new Inventory();
        noCoinState = new IdleState();
        hasMoneyState = new HasMoneyState();
        dispenseState = new DispenseState();
        outOfStockState = new OutOfStockState();
        currentState = noCoinState;
        balance=0;
        enteredAmount=0;
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public VendingState getNoCoinState() { 
        return noCoinState;
    }
    public VendingState getHasMoneyState() { 
        return hasMoneyState;
    }
    public VendingState getDispenseState() { 
        return dispenseState; 
    }
    public VendingState getOutOfStockState() { 
        return outOfStockState;
    }

    public void setInsertedCoin(int c){
        enteredAmount+=c;
        balance+=c;
    }

    public int getBalance(){
        return balance;
    }


    public int getEnteredAmount(){
        return enteredAmount;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void addItem(Item i, int quantity){
        inventory.addItem(i, quantity);
    }


    public void returnCoins(){
        balance-=enteredAmount;
        System.out.println("Amount retruned succesfully: Rs. "+ enteredAmount);
        enteredAmount=0;
    }

    public void getItemPrice(String code){
        inventory.getItem(code).getPrice();
    }

    public void getChange(int returnAmount){
        balance-=returnAmount;
        enteredAmount-=returnAmount;
        System.out.println("Returning amount.. Rs. "+ returnAmount);
    }

    public Item getSelectedItem(){
        return selectedItem;
    }

    public void setSelectedItem(Item i){
        selectedItem = i;
    }



    //user functions
    public void insertCoin(int c){
        this.currentState = currentState.insertCoin(instance, c);
    }

    public void dispense(){
        this.currentState = currentState.dispense(instance);
    }

    public void refill(Item i, int quantity){
        this.currentState = currentState.refill(instance, i, quantity);
    }

    public void refund(){
        this.currentState = currentState.refund(instance);
    }

    public void selectItem(String c){
        this.currentState = currentState.selectItem(instance, c);
    }
    
}