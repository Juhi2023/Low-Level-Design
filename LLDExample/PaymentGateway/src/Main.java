import factory.GatewayFactory;
import factory.GatewayType;
import service.PaymentService;
import service.PaymentRequest;


class Main{
    public static void main(String args[]){
        PaymentService s = PaymentService.getInstance();
        s.setGateway(GatewayFactory.getInstance().getGateway(GatewayType.PAYTM));
        PaymentRequest r = new PaymentRequest("juhi", "nandini", 100, "INR");

        s.processPayment(r);
    }
}