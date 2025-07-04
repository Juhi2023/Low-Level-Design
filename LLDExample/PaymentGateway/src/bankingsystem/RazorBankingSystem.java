package bankingsystem;
import java.util.*;

public class RazorBankingSystem implements IBankingSystem{

    private Random rand = new Random();
    public RazorBankingSystem() {}

    @Override
    public boolean processPayment(double amount) {
        int r = rand.nextInt(100);
        return r < 90;
    }
}