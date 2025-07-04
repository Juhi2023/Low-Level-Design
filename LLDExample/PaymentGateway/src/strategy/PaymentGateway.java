package strategy;

import bankingsystem.IBankingSystem;
import service.PaymentRequest;


public abstract class PaymentGateway{
    protected IBankingSystem bankingsystem;
    
    public PaymentGateway() {
        this.bankingsystem = null;
    }
    public boolean processPayment(PaymentRequest r){
        if (!validatePayment(r)) {
            System.out.println("[PaymentGateway] Validation failed for " + r.sender + ".");
            return false;
        }
        if (!initiatePayment(r)) {
            System.out.println("[PaymentGateway] Initiation failed for " + r.sender + ".");
            return false;
        }
        if (!confirmPayment(r)) {
            System.out.println("[PaymentGateway] Confirmation failed for " + r.sender + ".");
            return false;
        }
        return true;
    }

    public abstract boolean validatePayment(PaymentRequest r);
    public abstract boolean initiatePayment(PaymentRequest r);
    public abstract boolean confirmPayment(PaymentRequest r);

}