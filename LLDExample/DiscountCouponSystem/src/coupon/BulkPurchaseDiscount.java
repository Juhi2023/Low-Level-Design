package coupon;

import strategy.*;
import manager.*;
import enums.*;
import models.*;

public class BulkPurchaseDiscount  extends Coupon{
    double flat;
    double threshold;
    DiscountStrategy strategy;

    public BulkPurchaseDiscount (double flat, double threshold){
        this.flat = flat;
        this.threshold = threshold;
        strategy = DiscountStrategyManager.getInstance().getStrategy(StrategyType.FLAT, flat, 0);
    }


    public boolean isApplicable(Cart cart) {
        return cart.getCurrentTotal() >= threshold;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculate(cart.getCurrentTotal());
    }

    public String getName(){
        return "Bulk Purchase Rs "+ flat + " Off";
    }
    
}