package strategy;

import bankingsystem.IBankingSystem;
import bankingsystem.PaytmBankingSystem;
import service.PaymentRequest;


public class PaymentGatewayProxy extends  PaymentGateway{

    private PaymentGateway realGateway;
    private int retries;
    
    public PaymentGatewayProxy(PaymentGateway p, int r) {
        this.realGateway = p;
        this.retries = r;
    }

     public boolean processPayment(PaymentRequest r) {
        boolean result = false;
        for (int attempt = 0; attempt < retries; ++attempt) {
            if (attempt > 0) {
                System.out.println("[Proxy] Retrying payment (attempt " + (attempt+1) + ") for " + r.sender + ".");
            }
            result = realGateway.processPayment(r);
            if (result) 
                break;
        }
        if (!result) {
            System.out.println("[Proxy] Payment failed after " + retries + " attempts for " + r.sender + ".");
        }
        return result;
    }


    public boolean validatePayment(PaymentRequest request) {
        return realGateway.validatePayment(request);
    }

     public boolean initiatePayment(PaymentRequest request) {
        return realGateway.initiatePayment(request);
    }

     public boolean confirmPayment(PaymentRequest request) {
        return realGateway.confirmPayment(request);
    }

}