package food;

public class BonusFood extends FoodItem{
    public BonusFood(int row, int col, int points){
        super(row, col);
        this.points = points;
    }
}