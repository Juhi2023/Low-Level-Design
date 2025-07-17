package coupon;

import strategy.*;
import manager.*;
import enums.*;
import models.*;

public class SeasonalOffer extends Coupon{
    double percent;
    String category;
    DiscountStrategy strategy;

    public SeasonalOffer(String cat, double percent){
        this.percent = percent;
        category = cat;
        strategy = DiscountStrategyManager.getInstance().getStrategy(StrategyType.PERCENT, percent, 0);
    }


    public boolean isApplicable(Cart cart) {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double getDiscount(Cart cart) {
        double subtotal = 0.0;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getCategory().equals(category)) {
                subtotal += item.getItemsTotal();
            }
        }
        return strategy.calculate(subtotal);
    }

    public String getName(){
        return "Seasonal Offer "+ percent + "% Off on " + category;
    }
    
}