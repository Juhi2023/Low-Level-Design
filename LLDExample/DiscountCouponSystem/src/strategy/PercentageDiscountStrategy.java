package strategy;

public class PercentageDiscountStrategy implements DiscountStrategy{
    private double precentage;

    public PercentageDiscountStrategy(double precentage) {
        this.precentage = precentage;
    }

    public double calculate(double baseAmount) {
        return baseAmount * precentage / 100;
    }
}