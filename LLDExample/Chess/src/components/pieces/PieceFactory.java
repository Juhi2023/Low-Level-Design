package components.pieces;
import enums.*;

public class PieceFactory{
    public static Piece createPiece(PieceType t, Color color){
        switch (t) {
            case KING: return new King(color);
            case QUEEN: return new Queen(color);
            case ROOK: return new Rook(color);
            case BISHOP: return new Bishop(color);
            case KNIGHT: return new Knight(color);
            case PAWN: return new Pawn(color);
            default: return null;
        }
    }
}