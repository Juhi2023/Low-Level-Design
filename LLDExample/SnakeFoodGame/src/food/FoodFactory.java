package food;

public class FoodFactory{

    public static FoodItem createFood(String type, int row, int col){
        if(type=="NORMAL"){
            return new NormalFood(row, col);
        }
        return null;
    }

    public static FoodItem createFood(String type, int row, int col, int points){
        if(type=="BONUS"){
            return new BonusFood(row, col, points);
        }
        return null;
    }
}