package models;

public class Account{
    String accountNo;
    int balance;

    public Account(String accountNo, int balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getNumber(){
        return accountNo;
    }

    public int getBalance(){
        return balance;
    }

    public void deposit(int b){
        balance = balance+b;
    }
    
    public void withdraw(int b){
        balance = balance-b;
    }
}