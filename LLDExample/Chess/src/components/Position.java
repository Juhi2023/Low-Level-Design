package components;

import java.util.*;
public class Position{
    private int row;
    private int col;

    public Position(int r, int c){
        row=r;
        col=c;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public boolean isValid(){
        return row>=0 && row<8 && col>=0 && col<8;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj) 
            return true;

        if(obj==null)
            return false;

        Position other = (Position) obj;
        return row== other.getRow() && col==other.getCol();
    }

    @Override
    public int hashCode(){
        return Objects.hash(row, col);
    }

}