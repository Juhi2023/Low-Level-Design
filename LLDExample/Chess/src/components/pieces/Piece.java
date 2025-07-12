package components.pieces;

import enums.Color;
import enums.PieceType;
import components.*;
import java.util.*;

public abstract class Piece{
    protected Color color;
    protected PieceType type;
    protected boolean hasMoved;

    public Piece(Color c, PieceType t) {
        color = c;
        type = t;
        hasMoved = false;
    }
    
    public Color getColor() { 
        return color; 
    }

    public PieceType getType() { 
        return type; 
    }

    public boolean getHasMoved() { 
        return hasMoved; 
    }

    public void setMoved(boolean moved) { 
        hasMoved = moved; 
    }

    public abstract List<Position> getPossibleMoves(Position currPos, Board board);
    public abstract String getSymbol();

    public String toString(){
        return (color == Color.WHITE ? "W" : "B") + getSymbol();
    }
}