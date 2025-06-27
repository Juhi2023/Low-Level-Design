import notification.INotification;
import observable.NotificationObservable;
import observable.IObservable;
import java.util.*;

public class NotificationService{
    private IObservable o;
    private List<INotification> notifications = new ArrayList<>();
    private static NotificationService instance;

    private NotificationService() {
        o = new NotificationObservable();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public IObservable getObservable(){
        return o;
    }

    public void sendNotification(INotification n){
        notifications.add(n);
        o.setNotification(n);
    }

}