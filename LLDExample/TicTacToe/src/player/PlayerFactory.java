package player;

import components.Symbol;

public class PlayerFactory{
    public static Player createPlayer(String type, String name, Symbol s){
        if(type.equals("Human")){
            return new HumanPlayer(name, s);
        }

        System.out.println("Invalid User.");
        return null;
    }
}