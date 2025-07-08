package observer;

public class ConsoleNotifier implements GameObserver{

    public void update(String msg){
        System.out.println("[Notification]: "+ msg);
    }
}