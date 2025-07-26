package dispenser;

public interface IDispenser{
    void setNextDispenser(IDispenser s);
    void dispene(int amount);
    boolean canDispense(int amount);
}