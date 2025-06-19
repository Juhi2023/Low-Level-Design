import paymentstrategy.StripePayment;
import paymentstrategy.PaymentInterface;

public class Main {
  public static void main(String args[]) {
    PaymentInterface stripe = new StripePayment();
    PaymentProcessor processor = new PaymentProcessor(stripe); 
    processor.processPayment();
  }
}

//javac -d out src/*.java src/paymentstrategy/*.java
//java -cp out Main