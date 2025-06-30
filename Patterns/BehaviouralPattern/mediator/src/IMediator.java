public interface IMediator{
    void registerUser(User u);
    void send(String from, String msg);
    void sendPrivate(String from, String to, String msg);
}