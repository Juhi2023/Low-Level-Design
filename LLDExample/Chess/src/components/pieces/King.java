package components.pieces;

import java.util.*;
import enums.Color;
import enums.PieceType;
import components.*;

public class King extends Piece{
    public King(Color c) {
        super(c, PieceType.KING);
    }
    

    public List<Position> getPossibleMoves(Position currPos, Board board){
        List<Position> moves = new ArrayList<>();
        int directions[][]={{-1, -1}, {1,1}, {-1,1},{1,-1},{0,1},{1,0},{-1,0},{-1,0}};
        int row = currPos.getRow();
        int col = currPos.getCol();

        for(int j=0; j<8; j++){
            Position newPos = new Position(row + directions[j][0], col + directions[j][1]);
            if(newPos.isValid() && board.isOccupiedBySameColor(newPos, color)){
                moves.add(newPos);
            }

        }
        return moves;
    }

    public String getSymbol(){
        return "K";
    }

}