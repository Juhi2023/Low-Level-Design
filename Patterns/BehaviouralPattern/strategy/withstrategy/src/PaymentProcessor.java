import paymentstrategy.PaymentInterface;

public class PaymentProcessor {
  private PaymentInterface paymentStrategy;

  public PaymentProcessor(PaymentInterface paymentStrategy) {
    this.paymentStrategy = paymentStrategy;
  }

  public void processPayment() {
    paymentStrategy.processPayment();
  }

  public void setPaymentStrategy(PaymentInterface paymentStrategy) {
    this.paymentStrategy = paymentStrategy;
  }
}