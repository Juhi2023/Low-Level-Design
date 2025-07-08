import observer.GameObserver;

public interface GameObservable{
    void addObserver(GameObserver o);
    void removeObserver(GameObserver o);
    void notify(String msg);
}