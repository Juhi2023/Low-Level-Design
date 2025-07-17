package coupon;

import strategy.*;
import manager.*;
import enums.*;
import models.*;

public class BankingCoupon extends Coupon {
    private String bank;
    private double minSpend;
    private double percent;
    private double offCap;
    private DiscountStrategy strat;

    public BankingCoupon(String b, double ms, double percent, double offCap) {
        this.bank    = b;
        this.minSpend= ms;
        this.percent = percent;
        this.offCap  = offCap;
        this.strat   = DiscountStrategyManager.getInstance().getStrategy(StrategyType.PERCENT_WITH_CAP, percent, offCap);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getPaymentBank().equals(bank) && cart.getOrignalTotal() >= minSpend;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strat.calculate(cart.getCurrentTotal());
    }

    @Override
    public String getName() {
        return bank + " Bank Rs " + (int)percent + " off upto " + (int)offCap;
    }
}