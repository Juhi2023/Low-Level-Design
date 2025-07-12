package components.pieces;

import java.util.*;
import enums.Color;
import enums.PieceType;
import components.*;

public class Knight extends Piece{
    public Knight(Color c) {
        super(c, PieceType.KNIGHT);
    }
    

    public List<Position> getPossibleMoves(Position currPos, Board board){
        List<Position> moves = new ArrayList<>();
        int directions[][]={{-2,-1}, {-2,1}, {-1,-2}, {-1,2}, {1,-2}, {1,2}, {2,-1}, {2,1}};
        int row = currPos.getRow();
        int col = currPos.getCol();

        for(int i=0; i<8; i++){
            Position newPos = new Position(row + directions[i][0], col + directions[i][1]);
            if(newPos.isValid() && board.isOccupiedBySameColor(newPos, color)){
                moves.add(newPos);
            }
        }
        return moves;
    }

    public String getSymbol(){
        return "N";
    }

}