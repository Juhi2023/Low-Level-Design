package controller;

import java.util.*;
import models.*;
import state.*;
import dispenser.*;
import enums.*;


public class ATM{
    static ATM instance;

    Bank bank;
    Card currentCard;
    IATMState currentState;
    NoteDispenser cashDispenser;

    private ATM(){
        bank = new Bank();
        currentCard=null;
        currentState = new IdleState();

        NoteDispenser c1 = new NoteDispenser1000(10); // 10 x $100 notes
        NoteDispenser c2 = new NoteDispenser500(20); // 20 x $50 notes
        NoteDispenser c3 = new NoteDispenser100(30); // 30 x $20 notes
        c1.setNextDispenser(c2);
        c2.setNextDispenser(c3);
        this.cashDispenser = c1;
    }

    public static ATM getInstance(){
        if(instance==null){
            instance = new ATM();
        }
        return instance;
    }

    public void setCurrentState(IATMState s){
        currentState = s;
    }

    public void setCurrentCard(Card c){
        currentCard = c;
    }

    public Card getCurrentCard(){
        return currentCard;
    }

    public Card getCardDetail(String cardNo){
        return bank.getCard(cardNo);
    }

    public void checkBalance(){
        System.out.println("Your current balance is: "+bank.getBalance(currentCard));
    }

    public void withdraw(int amount){
        int balance = bank.getBalance(currentCard);
        if(balance<amount){
            System.out.println("Insufficient amouont.");
        }else if(!cashDispenser.canDispense(amount)){
            System.out.println("Insufficient cash available in the ATM.");
        }else{
            cashDispenser.dispene(amount);
            bank.withdraw(currentCard, amount);
        }
    }

    public void deposit(int amount){
        bank.deposit(currentCard, amount);
    }




    public void insertCard(String cardNumber) {
        currentState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int amount) { 
        currentState.selectOption(this, op, amount); 
    }


}