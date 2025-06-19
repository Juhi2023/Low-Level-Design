package paymentstrategy;

public class PayPalPayment implements PaymentInterface {
  public void processPayment() {
    System.out.println("Processing PayPal payment...");
  }
}
