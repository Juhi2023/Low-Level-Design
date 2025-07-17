package coupon;

import models.*;

public abstract class Coupon{
    Coupon next;

    public Coupon(){
        next = null;
    }

    public void setNext(Coupon c){
        this.next = c;
    }

    public Coupon getNext(){
        return next;
    }

    public boolean isCombinable() {
        return true;
    }

    public void applyCoupon(Cart cart){
        if (isApplicable(cart)) {
            double discount = getDiscount(cart);
            cart.applyCoupon(discount);
            System.out.println(getName() + " applied: " + discount);
            if (!isCombinable()) {
                return;
            }
        }
        if (next != null) {
            next.applyCoupon(cart);
        }
    }

    public abstract boolean isApplicable(Cart cart);
    public abstract double getDiscount(Cart cart);
    public abstract String getName();
    
}