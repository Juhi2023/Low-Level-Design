package components;

public class Cell{
    private Symbol s;

    public Cell(){
        this.s =Symbol.EMPTY; 
    }

    public boolean isEmpty(){
        return s == Symbol.EMPTY;
    }

    public Symbol getSymbol(){
        return s;
    }

    public void setSymbol(Symbol s){
        this.s = s;
    }
}