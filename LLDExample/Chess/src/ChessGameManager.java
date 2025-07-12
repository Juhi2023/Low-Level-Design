import java.util.*;
import components.*;
import components.pieces.*;
import components.player.Player;
import enums.*;
class ChessGameManager{
    private Player whitePlayer;
    private Player blackPlayer ;
    private Board board;
    private ArrayList<Move> gameLog;
    private GameStatus status;
    private Color currentTurn;

    public ChessGameManager(Player p1, Player p2){
        whitePlayer = p1;
        blackPlayer  = p2;
        board = new Board();
        gameLog = new ArrayList<>();
        currentTurn = Color.WHITE;
        this.status = GameStatus.NOT_STARTED;
    }

    public boolean makeMove(Position from, Position to, Player player){
        Piece frompiece = board.getPiece(from);
        Piece topiece = board.getPiece(to);
        Color playerColor = player.getColor();

        if(frompiece==null|| frompiece.getColor() != playerColor){
            System.out.println("Invalid piece selection!");
            return false;
        }

        Move move = new Move(from, to, frompiece, topiece);
        if(!board.isValidMove(from, to, frompiece)){
            System.out.println("Invalid Move!");
            return false;
        }

        board.movePiece(from, to);
        gameLog.add(move);
        
        System.out.println(player.getName() + " moved " + frompiece.getSymbol() + " from ("+from.getRow() + ", " + from.getCol() + ") to ("  +to.getRow() + ", " + to.getCol() + ")");
        
        board.display();
        
        // Check game end conditions
        Color opponentColor = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;

        if (board.isCheckmate(opponentColor)) {
            System.out.println(opponentColor +" Won.");
            status = GameStatus.COMPLETED;
            return true;
        } 

        if (board.isCheckmate(opponentColor)) {
            System.out.println("Game Draw.");
            status = GameStatus.COMPLETED;
            return true;
        } 

        return true;
    }

    public void play(){
        this.status = GameStatus.IN_PROGRESS;
        board.display();
        while(status == GameStatus.IN_PROGRESS){
            Player currentPlayer = currentTurn == Color.WHITE ? whitePlayer : blackPlayer;
            Position newPos[] = currentPlayer.makeMove(board);
            boolean isMoved = makeMove(newPos[0], newPos[1], currentPlayer);
            if(isMoved){
                currentTurn = currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE;
            }else{
                continue;
            }

        }
        status = GameStatus.COMPLETED;
        System.out.println("Game Over!");
    }
}