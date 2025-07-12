package components.pieces;

import java.util.*;
import enums.Color;
import enums.PieceType;
import components.*;

public class Bishop extends Piece{
    public Bishop(Color c) {
        super(c, PieceType.BISHOP);
    }
    

    public List<Position> getPossibleMoves(Position currPos, Board board){
        List<Position> moves = new ArrayList<>();
        int directions[][]={{-1, -1}, {1,1}, {-1,1},{1,-1}};
        int row = currPos.getRow();
        int col = currPos.getCol();

        for(int i=0; i<4; i++){
            for(int j=1; j<8; j++){
                Position newPos = new Position(row + directions[i][0] * j, col + directions[i][1] * j);
                if(!newPos.isValid()){
                    break;
                }
                if(!board.isOccupiedBySameColor(newPos, color)){
                    break;
                }
                moves.add(newPos);
                if(board.isOccupied(newPos)){
                    break;
                }
            }
        }
        return moves;
    }

    public String getSymbol(){
        return "B";
    }

}