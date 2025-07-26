package models;

import java.util.*;

public class Bank{
    Map<String, Account> accounts;
    Map<String, Card> cards;
    //cardNO, accNo
    Map<String, String> cardsAccMap;
    static int cnt=0;
    static int cnt2=0;

    public Bank(){
        accounts = new HashMap<>();
        cards = new HashMap<>();
        cardsAccMap = new HashMap<>();

        // Create sample accounts and cards
        Account account1 = addAccount( 1000);
        Card card1 = addCard(account1.getNumber(), "1234");

        Account account2 = addAccount( 10000);
        Card card2 = addCard(account2.getNumber(), "4321");
    }

    public Account addAccount(int balance){
        String accNo = cnt++ + "ACC";
        Account acc = new Account(accNo, balance);
        accounts.put(accNo, acc);
        System.out.println("\nBank Account created Successfully with Account Number: "+accNo);
        return acc;
    }

    public Card addCard(String accNo, String pin){
        String cardNo = cnt2++ + "CARD";
        Card card = new Card(cardNo, pin);
        cards.put(cardNo, card);
        cardsAccMap.put(cardNo, accNo);
        System.out.println("Card created Successfully: "+cardNo);
        return card;
    }

    public int  getBalance(Card card){
        Account acc = accounts.get(cardsAccMap.get(card.getNumber()));
        return acc.getBalance();
    }

    public boolean authenticate(Card card, String pin){
        return card.getPin().equals(pin);
    }

    public void withdraw(Card c, int amount){
        Account acc = accounts.get(cardsAccMap.get(c.getNumber()));
        acc.withdraw(amount);
    }

    public void deposit(Card c, int amount){
        Account acc = accounts.get(cardsAccMap.get(c.getNumber()));
        acc.deposit(amount);
    }

    public Card getCard(String cardNo){
        return cards.getOrDefault(cardNo, null);
    }

    
}