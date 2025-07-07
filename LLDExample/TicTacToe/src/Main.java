import java.util.*;
import winstrategy.WinStrategy;
import winstrategy.RowWinStrategy;
import winstrategy.ColumnWinStrategy;
import winstrategy.DiagonalWinStrategy;
import observer.ConsoleNotifier;
import observer.GameObserver;
import player.PlayerFactory;
import player.Player;
import player.HumanPlayer;
import components.Symbol;

class Main{
    public static void main(String args[]){
        List<WinStrategy> strategies = new ArrayList<>();
        strategies.add(new RowWinStrategy());
        strategies.add(new ColumnWinStrategy());
        strategies.add(new DiagonalWinStrategy());

        List<Player> players = new ArrayList<>();
        players.add(PlayerFactory.createPlayer("Human", "Juhi", Symbol.O));
        players.add(PlayerFactory.createPlayer("Human", "Nandini", Symbol.X));

        

        TicTacToe game = new TicTacToe(3,  players, strategies);
        game.addObserver(new ConsoleNotifier());

        game.play();
    }
}