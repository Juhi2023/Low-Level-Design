package strategy;

import models.Warehouse;
import java.util.*;

public class WeeklyReplenishStrategy implements ReplenishStrategy {
    public WeeklyReplenishStrategy() {}

    @Override
    public void replenish(Warehouse w, Map<Integer,Integer> itemsToReplenish) {
        System.out.println("[WeeklyReplenish] Weekly replenishment triggered for inventory.");
    }
}