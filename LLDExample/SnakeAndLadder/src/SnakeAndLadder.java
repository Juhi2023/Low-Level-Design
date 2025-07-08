import java.util.*;
import components.Board;
import components.Player;
import components.Dice;
import observer.GameObserver;
import setupstrategy.SetupStrategy;

class SnakeAndLadder implements GameObservable{
    Board board;
    Deque<Player> players;
    List<GameObserver> observers;
    Dice dice;

    public SnakeAndLadder(int size, List<Player> players, SetupStrategy s){
        board = new Board(size);
        this.players = new LinkedList<>(players);
        this.observers = new ArrayList<>();
        dice = new Dice(6);

        board.setupBoard(s);
    }

    public void addObserver(GameObserver o){
        observers.add(o);
    }

    public void removeObserver(GameObserver o){
        observers.remove(o);
    }

    public void notify(String msg){
        for(GameObserver x : observers){
            x.update(msg);
        }
    }

    public void play(){
        // break code if entiies not entered correct;

        notify("Game Started!");

        while(true){
            Player currentPlayer = players.pollFirst();
            int move = dice.roll();
            int newPosition = currentPlayer.getPosition() + move;

            if (newPosition <= board.getSize()) {
                currentPlayer.setPosition(board.getNextPosition(newPosition));
                System.out.println(currentPlayer.getName() + " rolled a " + move +" and moved to position " + currentPlayer.getPosition());
            }

            if (currentPlayer.getPosition() == board.getSize()) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            players.addLast(currentPlayer);
        }

    }

}

