package state;

import controller.ATM;
import enums.OperationType;
import models.*;


public class AuthenticateState implements IATMState{
    public void insertCard(ATM machine, String cardNo){
        System.out.println("Error: Card already  has been inserted.");
    }

    public void enterPin(ATM machine, String pin){
        System.out.println("Error: PIN has already been entered and authenticated.");
    }

    public void selectOption(ATM machine, OperationType type, int amount){
        switch(type){
            case OperationType.CHECK_BALANCE: 
                machine.checkBalance();
                break;

            case OperationType.WITHDRAW_CASH:
                machine.withdraw(amount);
                break;

            case OperationType.DEPOSIT_CASH:
                machine.deposit(amount);
                break;
            default:
                break;
        }  
        System.out.println("Transaction complete.");
        ejectCard(machine);
    }

    public void ejectCard(ATM machine){
        System.out.println("Card ejected successfully.");
        machine.setCurrentCard(null);
        machine.setCurrentState(new IdleState());    
    }

}