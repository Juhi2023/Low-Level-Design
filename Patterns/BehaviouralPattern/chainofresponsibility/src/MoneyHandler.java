abstract class MoneyHandler{
    protected MoneyHandler nextHandler;

    public MoneyHandler(){
        this.nextHandler= null;
    }

    public void setNextHandler(MoneyHandler m){
        this.nextHandler = m;
    }

    abstract public void dispense(int ammount);
}