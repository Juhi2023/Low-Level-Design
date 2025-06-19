package withoutstrategy;

public class Main {

  public static void main(String args[]) {
    PaymentProcessor p =new PaymentProcessor();
    p.processPayment("Crypto");
  }
}



public class PaymentProcessor {

  public void processPayment(String paymentMethod) {
    if (paymentMethod.equals("CreditCard")) {
        System.out.println("Processing credit card payment...");
    } else if (paymentMethod.equals("PayPal")) {
        System.out.println("Processing PayPal payment...");
    } else if (paymentMethod.equals("Crypto")) {
        System.out.println("Processing crypto payment...");
    } else if (paymentMethod.equals("Stripe")) { // New method added
        System.out.println("Processing Stripe payment...");
    } else {
        System.out.println("Payment method not supported.");
    }
  }
}

