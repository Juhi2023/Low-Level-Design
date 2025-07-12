package components;

import components.*;
import components.pieces.*;
import enums.Color;
import enums.PieceType;
import java.util.*;

public class Board{
    Piece [][] board;
    Map<Position, Piece> piecePosition;

    public Board(){
        board = new Piece[8][8];
        piecePosition = new HashMap<>();
        initializeBoard();
    }

    public void initializeBoard(){
        placePiece(new Position(7, 0), PieceFactory.createPiece(PieceType.ROOK, Color.BLACK));
        placePiece(new Position(7, 1), PieceFactory.createPiece(PieceType.KNIGHT, Color.BLACK));
        placePiece(new Position(7, 2), PieceFactory.createPiece(PieceType.BISHOP, Color.BLACK));
        placePiece(new Position(7, 3), PieceFactory.createPiece(PieceType.QUEEN, Color.BLACK));
        placePiece(new Position(7, 4), PieceFactory.createPiece(PieceType.KING, Color.BLACK));
        placePiece(new Position(7, 5), PieceFactory.createPiece(PieceType.BISHOP, Color.BLACK));
        placePiece(new Position(7, 6), PieceFactory.createPiece(PieceType.KNIGHT, Color.BLACK));
        placePiece(new Position(7, 7), PieceFactory.createPiece(PieceType.ROOK, Color.BLACK));

        for(int i=0; i<8; i++){
            placePiece(new Position(6, i), PieceFactory.createPiece(PieceType.PAWN, Color.BLACK));
        }

        placePiece(new Position(0, 0), PieceFactory.createPiece(PieceType.ROOK, Color.WHITE));
        placePiece(new Position(0, 1), PieceFactory.createPiece(PieceType.KNIGHT, Color.WHITE));
        placePiece(new Position(0, 2), PieceFactory.createPiece(PieceType.BISHOP, Color.WHITE));
        placePiece(new Position(0, 3), PieceFactory.createPiece(PieceType.QUEEN, Color.WHITE));
        placePiece(new Position(0, 4), PieceFactory.createPiece(PieceType.KING, Color.WHITE));
        placePiece(new Position(0, 5), PieceFactory.createPiece(PieceType.BISHOP, Color.WHITE));
        placePiece(new Position(0, 6), PieceFactory.createPiece(PieceType.KNIGHT, Color.WHITE));
        placePiece(new Position(0, 7), PieceFactory.createPiece(PieceType.ROOK, Color.WHITE));

        for(int i=0; i<8; i++){
            placePiece(new Position(1, i), PieceFactory.createPiece(PieceType.PAWN, Color.WHITE));
        }
    }

    public void placePiece(Position pos, Piece p){
        board[pos.getRow()][pos.getCol()] = p;
        piecePosition.put(pos, p);
    }

    public void removePiece(Position pos) {
        board[pos.getRow()][pos.getCol()] = null;
        piecePosition.remove(pos);
    }

    public boolean isOccupied(Position pos) {
        return getPiece(pos) != null;
    }

    public Piece getPiece(Position pos) {
        return board[pos.getRow()][pos.getCol()];
    }

    public boolean isOccupiedBySameColor(Position pos, Color color){
        Piece p = getPiece(pos);
        return p !=null && p.getColor() == color;
    }

    public void movePiece(Position from , Position to){
        Piece piece = getPiece(from);
        if (piece != null) {
            Piece capturedPiece = getPiece(to);
            if (capturedPiece != null) {
                piecePosition.remove(to);
            }
            
            // Move the piece
            board[from.getRow()][from.getCol()] = null;
            board[to.getRow()][to.getCol()] = piece;
            
            // Update piece positions map
            piecePosition.remove(from);
            piecePosition.put(to, piece);
            
        }
    }

    //RULES -- can make another interface containing rules
    public boolean isValidMove(Position from, Position to, Piece p){
        List<Position> possibleMoves = p.getPossibleMoves(from, this);

        // Check if the target position is in possible moves
        boolean validDestination = false;
        for (Position x : possibleMoves) {
            if (x.equals(to)) {
                validDestination = true;
                break;
            }
        }
        
        return validDestination;
    }

    public Position findKing(Color color) {
        for (Position x : piecePosition.keySet()) {
            if (piecePosition.get(x).getType()== PieceType.KING && piecePosition.get(x).getColor() == color) {
                return x;
            }
        }
        return null; // Invalid position if not found
    }

    public List<Position> getAllPiecesOfColor(Color c){
        List<Position> res = new ArrayList<>();
        for(Position x : piecePosition.keySet()){
            Piece p = getPiece(x);
            if(p.getType()==PieceType.KING && c== p.getColor()){
                res.add(x);
            }
        }
        return res;
    }
    

    public boolean isInCheck(Color color) {
        Position kingPos = findKing(color);
        if (kingPos==null) 
            return false; //check if king is dead 

        //if king stuck in all directions from opponents
        Color opponentColor = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
        List<Position> opponentPieces = getAllPiecesOfColor(opponentColor);
        
        for (Position pos : opponentPieces) {
            Piece piece = getPiece(pos);
            List<Position> moves = piece.getPossibleMoves(pos, this);
            for (Position targetPos : moves) {
                if (targetPos.equals(kingPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(Color color) {
        if (!isInCheck(color)) 
            return false;

        //if king is checked then find is there any possible way to become save
        
        List<Position> pieces = getAllPiecesOfColor(color);
        for (Position pos : pieces) {
            Piece piece = getPiece(pos);
            List<Position> moves = piece.getPossibleMoves(pos, this);
            
            for (Position targetPos : moves) {
                Piece captured = getPiece(targetPos);
                movePiece(pos, targetPos);

                boolean stillInCheck = isInCheck(color);

                // Undo move
                movePiece(targetPos, pos);
                placePiece(targetPos, captured);

                if (!stillInCheck) {
                    return false; // Found a move to escape check
                }
            }
        }
        return true;
    }
    
    public boolean isStalemate(Color color) {
        if (isInCheck(color)) return false;
        
        List<Position> pieces = getAllPiecesOfColor(color);
        for (Position pos : pieces) {
            Piece piece = getPiece(pos);
            List<Position> moves = piece.getPossibleMoves(pos, this);
            
            for (Position targetPos : moves) {
                Piece captured = getPiece(targetPos);
                movePiece(pos, targetPos);

                boolean stillInCheck = isInCheck(color);

                // Undo move
                movePiece(targetPos, pos);
                placePiece(targetPos, captured);

                if (!stillInCheck) {
                    return false; // Found a move to escape check
                }
            }
        }
        return true;
    }
    

    public void display() {
        for(int j=0; j<8; j++){
            System.out.print(" " +j+ "   ");
        }
        System.out.println();
        for(int j=0; j<8; j++){
            System.out.print("---- ");
        }
        System.out.println();

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Piece p=getPiece(new Position(i, j));
                if(p==null){
                    System.out.print("|    ");
                }else{
                    System.out.print("| " +(p.getColor()==Color.WHITE ? "W" : "B")+p.getSymbol()+" ");
                }
            }
            System.out.print("|");
            System.out.println();
            for(int j=0; j<8; j++){
                System.out.print("---- ");
            }
            System.out.println();

        }

        for(int j=0; j<8; j++){
            System.out.print(" " +j+ "   ");
        }
        System.out.println();

        
    }
}
