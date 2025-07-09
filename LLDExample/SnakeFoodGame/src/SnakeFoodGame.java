import java.util.*;
import components.Board;
import components.Pair;
import movementstrategy.MovementStrategy;
import food. FoodItem;

public class SnakeFoodGame{
    private Board board;
    private Deque<Pair> snake;
    private Map<String, Boolean> snakeMap;
    private MovementStrategy movement;
    private List<FoodItem> food;
    private int foodIndex;


    public SnakeFoodGame(int width, int height, MovementStrategy strategy, List<FoodItem> food){
        this.board = Board.getInstance(width, height);
        Pair initialPos = new Pair(0, 0);
        this.snake = new LinkedList<>();
        this.snake.addFirst(initialPos);
        this.snakeMap = new HashMap<>();
        this.snakeMap.put("0-0", true);

        this.movement = strategy;
        this.food = food;
        this.foodIndex=0;
    }

    public int move(String direction){
        int s=0;
        Pair currHead = snake.peekFirst();
        Pair nextPos = movement.getNextPosition(currHead, direction);
        int r = nextPos.getRow();
        int c = nextPos.getCol();
        System.out.println("("+r+", "+c+")");

        //cross boundary
        if(r<0 || c<0 || r>= board.getHeight() || c>= board.getWidth()){
            System.out.println("Snake Collide with wall.");
            return -1;
        }


        //bites itself
        if(snakeMap.containsKey(r+"-"+c)){
            System.out.println("Snake Collide to itself.");
            return -1;
        }

        Pair currTail = snake.peekLast();

        //check if snake eats food
        boolean ateFood = r == food.get(foodIndex).getRow() && c == food.get(foodIndex).getCol();
        if(ateFood){
            s=food.get(foodIndex).getPoints();
            foodIndex++;
        }else{
            // If no food eaten, remove tail
            snake.pollLast();
            snakeMap.remove(currTail.getRow()+"-"+currTail.getCol());
        }

        //add new head
        snake.addFirst(nextPos);
        snakeMap.put(r+"-"+c, true);

        return s;
    }

    public void play(){
        int score=0;
        Scanner scanner = new Scanner(System.in);
        boolean isGameOver=false;

        System.out.println("===== SNAKE GAME =====");

        while(!isGameOver){
            if(foodIndex==food.size()){
                System.out.println("Score: " + score);
                break;
            }

            displayGameState();
            String direction = scanner.nextLine().toUpperCase();
            // Skip invalid inputs
            if (direction.isEmpty()) {
                System.out.println("Invalid input! Use U/D/L/R to move.");
                continue;
            }

            int foodScore = move(direction);
            if(foodScore==-1){
                System.out.println("--------------GAME OVER!---------------");
                System.out.println("Final score: " + (score));
                isGameOver = true;
            }else{
                score+=foodScore;
                System.out.println("Score: " + score);
            }
            
        }

        scanner.close();
        System.out.println("Thanks for playing!");
    }

    public void displayGameState() {
    int height = board.getHeight();
    int width = board.getWidth();

    char[][] grid = new char[width+1][height+1];

    // Fill with empty cells
    for (int i = 0; i < height; i++) {
        Arrays.fill(grid[i], '.');
    }

    // Mark the food (if not all eaten)
    if (foodIndex < food.size()) {
        FoodItem f = food.get(foodIndex);
        grid[f.getRow()][f.getCol()] = 'F';
    }

    // Mark the snake body
    for (Pair p : snake) {
        grid[p.getRow()][p.getCol()] = 'S';
    }

    // Mark the head differently
    Pair head = snake.peekFirst();
    grid[head.getRow()][head.getCol()] = 'H';

    // Print the grid
    System.out.println("Current Board:");
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            System.out.print(grid[i][j] + " ");
        }
        System.out.println();
    }

    System.out.println("Current snake length: " + snake.size());
}

}