package coupon;

import strategy.*;
import manager.*;
import enums.*;
import models.*;

public class LoyaltyDiscount extends Coupon{
    double percent;
    DiscountStrategy strategy;

    public LoyaltyDiscount(double percent){
        this.percent = percent;
        strategy = DiscountStrategyManager.getInstance().getStrategy(StrategyType.PERCENT, percent, 0);
    }


    public boolean isApplicable(Cart cart) {
        return cart.getIsLoyalityMember();
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculate(cart.getCurrentTotal());
    }

    public String getName(){
        return "Loyality Discount "+ percent + "% Off ";
    }
    
}