package service;

import strategy.PaymentGateway;


public class PaymentService {
    private static PaymentService instance = new PaymentService();
    private PaymentGateway gateway;

    private PaymentService(){
        gateway=null;
    }

    public static PaymentService getInstance() {
        return instance;
    }

    public void setGateway(PaymentGateway g){
        gateway = g;
    }

    public boolean processPayment(PaymentRequest request) {
        if (gateway == null) {
            System.out.println("[PaymentService] No payment gateway selected.");
            return false;
        }
        
        return gateway.processPayment(request);
    }
}