package state;

import controller.ATM;
import enums.OperationType;
import models.*;

public class HasCardState implements IATMState{
    public void insertCard(ATM machine, String cardNo){
        System.out.println("\nCard already  has been inserted.");
    }

    public void enterPin(ATM machine, String pin){
        Card card = machine.getCurrentCard();
        if(card.getPin().equals(pin)){
            System.out.println("Authentication successful");    
            machine.setCurrentState(new AuthenticateState());    
        }else{
            System.out.println("Invalid pin.");
            ejectCard(machine);    
        }
    }

    public void selectOption(ATM machine, OperationType type, int amount){
        System.out.println("Error: Please enter pin first.");        
    }

    public void ejectCard(ATM machine){
        System.out.println("Card ejected successfully.");
        machine.setCurrentCard(null);
        machine.setCurrentState(new IdleState());    
    }

}