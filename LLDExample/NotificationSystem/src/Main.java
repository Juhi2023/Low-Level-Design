import observable.IObservable;
import observable.NotificationObservable;
import observer.IObserver;
import observer.Logger;
import observer.NotificationEngine;
import paymentstrategy.INotificationStrategy;
import paymentstrategy.EmailStrategy;
import paymentstrategy.SMSStrategy;
import paymentstrategy.PopUpStrategy;
import notification.INotification;
import notification.SimpleNotification;
import notification.INotificationDecorator;
import notification.TimeStampDecorator;
import java.util.*;

class Main{

    public static void main(String args[]){
        NotificationService ns = NotificationService.getInstance();
        IObservable o = ns.getObservable();

        Logger observer1 = new Logger(o);
        NotificationEngine  observer2 = new NotificationEngine(o);

        observer2.addNotificationStrategy(new EmailStrategy("juhi@gmail.com"));
        observer2.addNotificationStrategy(new SMSStrategy("42342342342"));
        observer2.addNotificationStrategy(new PopUpStrategy());

        o.addObserver(observer1);
        o.addObserver(observer2);

        INotification noti1 = new SimpleNotification("Hi, this is juhi.");
        INotification noti2 = new TimeStampDecorator(new SimpleNotification("Hi, this is juhi."));

        ns.sendNotification(noti1);
        ns.sendNotification(noti2);
    }
}