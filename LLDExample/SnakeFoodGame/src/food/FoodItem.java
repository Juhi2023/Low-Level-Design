package food;

public abstract class FoodItem{
    protected int points;
    private int row;
    private int col;

    public FoodItem(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public int getRow(){return row;}
    public int getCol(){return col;}
    public int getPoints(){return points;}
}