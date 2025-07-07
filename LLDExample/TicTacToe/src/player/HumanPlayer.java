package player;

import java.util.*;
import components.Symbol;
import components.Board;

public class HumanPlayer extends Player{
    public HumanPlayer(String name, Symbol s){
        super(name, s);
    }

    public int[] makeMove(Board b){
        System.out.println("Enter move position: ");
        Scanner sc = new Scanner(System.in);
        int row =  sc.nextInt();
        int col = sc.nextInt();

        return new int[]{row, col};
    }
}