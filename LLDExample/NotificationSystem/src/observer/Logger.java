package observer;

import observable.IObservable;

public class Logger implements IObserver{
    IObservable o;

    public Logger(IObservable o){
        this.o = o;
    }

    public void update(){
        System.out.println("Logging New Notification : \n" + o.getNotificationContent());
    };
}