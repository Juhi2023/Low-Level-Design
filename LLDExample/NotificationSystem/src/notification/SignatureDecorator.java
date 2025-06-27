package notification;

public class SignatureDecorator extends INotificationDecorator{

    public SignatureDecorator(INotification n){
        super(n);
    }

    public String getContent(){
        return "Sign: " + notification.getContent();
    }
}