package notification;

public class SimpleNotification implements INotification{
    private String content;

    public SimpleNotification(String text){
        this.content = text;
    }

    public String getContent(){
        return content;
    }
}