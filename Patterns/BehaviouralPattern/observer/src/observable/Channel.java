package observable;
import observer.ISubscriber;
import observer.Subscriber;
import java.util.ArrayList;
import java.util.List;

public class Channel implements IChannel{
    private List<ISubscriber> subscribers;
    private String name;
    private String lastestVideo;

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }
    
    @Override
    public void subscribe(ISubscriber subscriber){
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }
    
    @Override
    public void unsubscribe(ISubscriber subscriber){
        subscribers.remove(subscriber);
    }
    
    @Override
    public void notifySubscribers(){
        for(ISubscriber x: subscribers){
            x.update();
        }
    }

    public void uploadVideo(String title) {
        lastestVideo = title;
        System.out.println("\n[" + name + " uploaded \"" + title + "\"]");
        notifySubscribers();
    }

    public String getVideoData() {
        return "\nCheckout our new Video : " + lastestVideo + "\n";
    }
}