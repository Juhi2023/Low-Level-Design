package strategy;

import bankingsystem.IBankingSystem;
import bankingsystem.RazorBankingSystem;
import service.PaymentRequest;
import java.util.*;

public class RazorpayGateway extends  PaymentGateway{
    IBankingSystem bankingsystem;
    public RazorpayGateway() {
        this.bankingsystem = new RazorBankingSystem();
    }

    public boolean validatePayment(PaymentRequest r){
        System.out.println("[Razorpay] validating the request for "+ r.sender);
        if (r.amount <= 0 || !"INR".equals(r.currency)) {
            return false;
        }
        return true;
    }

    public boolean initiatePayment(PaymentRequest r){
        System.out.println("[Razorpay] initiating the payment of "+ r.amount + " "+r.currency);
        return bankingsystem.processPayment(r.amount);
    }

    public boolean confirmPayment(PaymentRequest r){
        System.out.println("[Razorpay] Confirming payment for " + r.sender + ".");
        // Confirmation always succeeds in this simulation
        return true;
    }

}