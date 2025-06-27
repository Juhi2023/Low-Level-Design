package observer;

import java.util.*;
import observable.IObservable;
import paymentstrategy.INotificationStrategy;

public class NotificationEngine implements IObserver{
    IObservable o;
    private List<INotificationStrategy> notificationStrategies = new ArrayList<>();


    public NotificationEngine(IObservable o){
        this.o = o;
    }

    public void addNotificationStrategy(INotificationStrategy ns) {
        this.notificationStrategies.add(ns);
    }

    public void update() {
        String notificationContent = o.getNotificationContent();
        for (INotificationStrategy strategy : notificationStrategies) {
            strategy.sendNotification(notificationContent);
        }
    }
}