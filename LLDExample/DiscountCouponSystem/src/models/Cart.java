package models;

import java.util.*;

public class Cart{
    private List<CartItem> items;
    private double orignalTotal;
    private double currentTotal;

    private boolean isLoyalityMember;  //contain at user level
    private String paymentBank;

    public Cart(){
        this.items = new ArrayList<>();
        this.orignalTotal = 0.0;
        this.currentTotal = 0.0;

        this.isLoyalityMember = false;
    }

    public double getOrignalTotal(){
        return orignalTotal;
    }

    public double getCurrentTotal(){
        return orignalTotal;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addProduct(Product p, int quantity){
        CartItem item = new CartItem(p, quantity);
        items.add(item);
        orignalTotal  += p.getPrice();
        currentTotal += p.getPrice();
    }

    public void setIsLoyalityMember(boolean isLoyalityMember){
        this.isLoyalityMember = isLoyalityMember;
    }

    public boolean getIsLoyalityMember(){
        return isLoyalityMember;
    }

    public void setPaymentBank(String bank) {
        this.paymentBank = bank;
    }

    public String getPaymentBank() {
        return paymentBank;
    }


    public void applyCoupon(double discount){
            currentTotal -= discount;
        if(currentTotal<0){
            currentTotal = 0.0;
        }
    }
}   