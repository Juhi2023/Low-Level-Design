package winstrategy;

import components.Board;
import components.Symbol;

public class RowWinStrategy implements WinStrategy{
    @Override
    public boolean checkWin(Board b, Symbol s){
        int size = b.size;
        for(int i=0; i<size; i++){
            boolean match = true;
            for(int j=0; j< size; j++){
                if(b.getSymbol(i, j)!=s){
                    match = false;
                    break;
                }
            }
            if(match){
                return true;
            }
        }
        return false;
    }
}