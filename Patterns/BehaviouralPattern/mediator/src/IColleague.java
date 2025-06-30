public interface IColleague{
    void send(String msg);
    void sendPrivate(String to, String msg);
    void recieve(String from, String msg);
}