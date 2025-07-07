package player;

import components.Symbol;
import components.Board;

public abstract class Player{
    public String name;
    public Symbol s;

    public Player(String name, Symbol s){
        this.name = name;
        this.s =s;
    }

    public abstract int[] makeMove(Board b);
}