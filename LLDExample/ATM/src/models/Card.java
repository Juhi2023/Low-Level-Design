package models;

public class Card{
    String cardNo;
    String pin;

    public Card(String cardNo, String pin){
        this.cardNo = cardNo;
        this.pin = pin;
    }

    public String getNumber(){
        return cardNo;
    }

    public String getPin(){
        return pin;
    }
}