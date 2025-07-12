import components.player.HumanPlayer;
import components.player.Player;
import components.pieces.*;
import enums.*;

class Main{
    public static void main(String args[]){
        Player whitePlayer = new HumanPlayer("Juhi", Color.WHITE);
        Player blackPlayer  = new HumanPlayer("Nandini", Color.BLACK);
        ChessGameManager game = new ChessGameManager(whitePlayer, blackPlayer);
        game.play();
    }

}