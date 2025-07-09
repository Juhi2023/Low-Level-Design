import java.util.*;
import movementstrategy.HumanMovementStrategy;
import food.FoodItem;
import food.FoodFactory;
import food.NormalFood;
import food.BonusFood;

class Main{
    public static void main(String args[]){
        List<FoodItem> food = new ArrayList<>();

        food.add(FoodFactory.createFood("NORMAL", 3, 4));
        food.add(FoodFactory.createFood("NORMAL", 5, 3));
        food.add(FoodFactory.createFood("NORMAL", 1, 4));
        food.add(FoodFactory.createFood("BONUS", 3, 4, 6));
        food.add(FoodFactory.createFood("BONUS", 7, 1, 10));

        SnakeFoodGame game = new SnakeFoodGame(7, 6, new HumanMovementStrategy(), food);
        game.play();
    }
}