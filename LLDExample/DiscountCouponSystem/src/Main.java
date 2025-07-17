import manager.*;
import java.util.*;
import models.*;
import coupon.*;

class Main{
    public static void main(String args[]){
        CouponManager mgr = CouponManager.getInstance();
        mgr.addCoupon(new SeasonalOffer("Clothing", 10));
        mgr.addCoupon(new LoyaltyDiscount(5));


        Product p1 = new Product("Jacket", "Clothing", 1000);
        Product p3 = new Product("Jeans", "Clothing", 1000);
        Product p4 = new Product("Headphones", "Electronics", 2000);

        Cart cart = new Cart();
        cart.addProduct(p1, 1);
        cart.addProduct(p3, 2);
        cart.addProduct(p4, 1);
        cart.setIsLoyalityMember(true);

        System.out.println("Original Cart Total: " + cart.getOrignalTotal() + " Rs");

        List<String> applicable = mgr.getApplicableCoupons(cart);
        System.out.println("Applicable Coupons:");
        System.out.println(applicable);

        double finalTotal = mgr.applyAll(cart);
        System.out.println("Final Cart Total after discounts: " + finalTotal + " Rs");
    }
}