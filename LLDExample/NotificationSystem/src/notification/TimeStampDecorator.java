package notification;
public class TimeStampDecorator extends INotificationDecorator{

    public TimeStampDecorator(INotification n){
        super(n);
    }

    public String getContent(){
        return "[20:23 PM] " + notification.getContent();
    }
}