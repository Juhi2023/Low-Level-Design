package observable;

import java.util.*;
import observer.IObserver;
import notification.INotification;

public class NotificationObservable implements IObservable{
    private List<IObserver> observers = new ArrayList<>();
    private INotification currentNotification;

    public void addObserver(IObserver o){
        observers.add(o);
    }

    public void removeObserver(IObserver o){
        observers.remove(o);
    }

    public void notifyObserver(){
        for(IObserver i : observers){
            i.update();
        }
    }

    public void setNotification(INotification n){
        currentNotification = n;
        notifyObserver();
    }

    public String getNotificationContent(){
        return currentNotification.getContent();
    }
}