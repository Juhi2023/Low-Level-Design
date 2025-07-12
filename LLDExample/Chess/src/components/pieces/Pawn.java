package components.pieces;

import java.util.*;
import enums.Color;
import enums.PieceType;
import components.*;

public class Pawn extends Piece{
    public Pawn(Color c) {
        super(c, PieceType.PAWN);
    }
    

    public List<Position> getPossibleMoves(Position currPos, Board board){
        List<Position> moves = new ArrayList<>();
        int dir = (color == Color.WHITE) ? 1 : -1;

        int row = currPos.getRow();
        int col = currPos.getCol();
        
        Position oneStep = new Position(row +dir, col);
        if(oneStep.isValid() && !board.isOccupied(oneStep)){
            moves.add(oneStep);

            Position twoStep = new Position(row + 2 * dir, col);
            if(!hasMoved && twoStep.isValid() && !board.isOccupied(twoStep)){
                moves.add(twoStep);
            }
        }

        for (int dc : new int[]{-1, 1}) {
            Position diag = new Position(row + dir, col + dc);
            if (diag.isValid() && board.isOccupied(diag) && !board.isOccupiedBySameColor(diag, color)) {
                moves.add(diag);
            }
        }

        return moves;
    }
    
    public String getSymbol(){
        return "P";
    }

}