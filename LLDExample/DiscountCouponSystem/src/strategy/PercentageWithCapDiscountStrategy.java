package strategy;

public class PercentageWithCapDiscountStrategy implements DiscountStrategy{
    private double precentage;
    private double cap;

    public PercentageWithCapDiscountStrategy(double precentage, double cap) {
        this.precentage = precentage;
        this.cap = cap;
    }

    public double calculate(double baseAmount) {
        double disc = (precentage / 100.0) * baseAmount;
        return disc > cap ? cap : disc;   
    }
}