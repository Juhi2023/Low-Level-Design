package state;

import controller.ATM;
import enums.OperationType;
import models.*;

public class IdleState implements IATMState{
    public void insertCard(ATM machine, String cardNo){
        System.out.println("\nCard has been inserted.");
        Card card = machine.getCardDetail(cardNo);

        if (card != null) {
            machine.setCurrentState(new HasCardState());
        }

        machine.setCurrentCard(card);
    }

    public void enterPin(ATM machine, String pin){
        System.out.println("Error: Please insert a card first.");
    }

    public void selectOption(ATM machine, OperationType type, int amount){
        System.out.println("Error: Please insert a card first.");        
    }

    public void ejectCard(ATM machine){
        System.out.println("Error: Card not found.");
    }

}