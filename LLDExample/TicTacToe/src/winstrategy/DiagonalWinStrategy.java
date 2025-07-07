package winstrategy;

import components.Board;
import components.Symbol;

public class DiagonalWinStrategy implements WinStrategy{
    @Override
    public boolean checkWin(Board b, Symbol s){
        int size = b.size;
        boolean match = true;
        for(int i=0; i<size; i++){
            if(b.getSymbol(i, i)!=s){
                match = false;
                break;
            }
        }

        if(match){
            return match;
        }

        //check anti diagonal
        match = true;
        for(int i=0; i<size; i++){
            if(b.getSymbol(i, size-1-i)!=s){
                match = false;
                break;
            }
        }
        return match;
    }
}