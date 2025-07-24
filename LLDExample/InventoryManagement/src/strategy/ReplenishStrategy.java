package strategy;

import models.Warehouse;
import java.util.*;

public interface ReplenishStrategy {
    void replenish(Warehouse w, Map<Integer,Integer> itemsToReplenish);
}