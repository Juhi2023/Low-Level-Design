package strategy;

import bankingsystem.IBankingSystem;
import bankingsystem.PaytmBankingSystem;
import service.PaymentRequest;


public class PaytmGateway extends  PaymentGateway{
    IBankingSystem bankingsystem;
    
    public PaytmGateway() {
        this.bankingsystem = new PaytmBankingSystem();
    }

    public boolean validatePayment(PaymentRequest r){
        System.out.println("[Paytm] validating the request for "+ r.sender);
        if (r.amount <= 0 || !"INR".equals(r.currency)) {
            return false;
        }
        return true;
    }

    public boolean initiatePayment(PaymentRequest r){
        System.out.println("[Paytm] initiating the payment of "+ r.amount + " "+r.currency);
        return bankingsystem.processPayment(r.amount);
    }

    public boolean confirmPayment(PaymentRequest r){
        System.out.println("[Paytm] Confirming payment for " + r.sender + ".");
        // Confirmation always succeeds in this simulation
        return true;
    }

}