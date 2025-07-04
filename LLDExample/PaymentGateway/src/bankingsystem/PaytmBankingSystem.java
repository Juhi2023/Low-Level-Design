package bankingsystem;
import java.util.*;

public class PaytmBankingSystem implements IBankingSystem{

    private Random rand = new Random();
    public PaytmBankingSystem() {}

    @Override
    public boolean processPayment(double amount) {
            int r = rand.nextInt(100);
            return r < 80;
        }
}