import java.util.*;
import components.Player;
import observer.GameObserver;
import observer.ConsoleNotifier;
import setupstrategy.HumanSetupStrategy;
import components.boardentity.BoardEntity;
import components.boardentity.Ladder;
import components.boardentity.Snake;

public class Main{
    public static void main(String args[]){
        GameObserver  o = new ConsoleNotifier();

        List<Player> players = new ArrayList<>();
        players.add(new Player("Juhi"));
        players.add(new Player("Nandini"));
        players.add(new Player("Shobha"));

        List<BoardEntity> entities = new ArrayList<>();
        entities.add(new Ladder(78, 90));
        entities.add(new Ladder(25, 50));
        entities.add(new Ladder(20, 61));
        entities.add(new Snake(2, 45));
        entities.add(new Snake(67, 95));
        entities.add(new Snake(56, 71));

        SnakeAndLadder game = new SnakeAndLadder(10, players, new HumanSetupStrategy(entities));
        game.addObserver(o);
        game.play();
    }
}