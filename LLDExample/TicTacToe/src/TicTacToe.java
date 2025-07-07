import java.util.*;
import player.Player;
import components.Board;
import observer.GameObserver;
import winstrategy.WinStrategy;

class TicTacToe implements GameObservable{
    private final Board board;
    private Deque<Player> players;
    private List<GameObserver> observers;

    public TicTacToe(int size, List<Player> players, List<WinStrategy> strategies) {
        this.board = new Board(size, strategies);
        this.players = new LinkedList<>(players);
        this.observers = new ArrayList<>();
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
        notify("Tic Tac Toe Game Started!");
        if(players.size() < 2) {
            System.out.println("Need at least 2 players!");
            return;
        }

        while(!board.isFull()){
            Player  p = players.peekFirst();
            int move[] =  p.makeMove(board);

            if(!board.isValid(move[0], move[1])){
                System.out.println("Invalid move! Try again.");
                continue;

            }
            board.placeMove(move[0], move[1], p.s);
            board.display();
            players.addLast(players.removeFirst());
            if(board.checkWin(p.s)){
                notify(p.name+" won.");
                board.display();
                break;
            }
            if(board.isFull()){
                notify("Game Draw.");
                board.display();
                break;
            }
        }
    }

}