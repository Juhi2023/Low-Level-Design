package paymentstrategy;

public class CreditCardPayment implements PaymentInterface {
  public void processPayment() {
    System.out.println("Processing credit card payment...");
  }
}
