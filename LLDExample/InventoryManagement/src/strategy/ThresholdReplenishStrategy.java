package strategy;

import models.Warehouse;
import java.util.*;

public class ThresholdReplenishStrategy implements ReplenishStrategy {
    private int threshold;

    public ThresholdReplenishStrategy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void replenish(Warehouse w, Map<Integer,Integer> itemsToReplenish) {
        System.out.println("[ThresholdReplenish] Checking threshold...");
        for (Map.Entry<Integer,Integer> it : itemsToReplenish.entrySet()) {
            int sku       = it.getKey();
            int qtyToAdd  = it.getValue();
            int current   = w.checkStock(sku);
            if (current < threshold) {
                w.addProduct(w.getProduct(sku), qtyToAdd);
                System.out.println("  -> SKU " + sku + " was " + current + ", replenished by " + qtyToAdd);
            }
        }
    }
}
