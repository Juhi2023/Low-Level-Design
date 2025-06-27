package notification;

public abstract class INotificationDecorator implements INotification{
    protected INotification notification;

    public INotificationDecorator(INotification n){
        notification = n;
    }

    abstract public String getContent();
}