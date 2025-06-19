package paymentstrategy;

public class StripePayment implements PaymentInterface {
  public void processPayment() {
    System.out.println("Processing Stripe payment...");
  }
}