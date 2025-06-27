package observable;
import observer.IObserver;
import notification.INotification;

public interface IObservable{
    public void setNotification(INotification n);
    public void addObserver(IObserver o);
    public void removeObserver(IObserver  o);
    public void notifyObserver();
    public String getNotificationContent();
}