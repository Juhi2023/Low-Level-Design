package manager;

import models.*;
import java.util.*;

public class InventoryManager {
    private static InventoryManager instance;
    private List<Warehouse> warehouses;

    private InventoryManager() {
        warehouses = new ArrayList<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void registerWarehouse(Warehouse ds) {
        warehouses.add(ds);
    }

    public List<Warehouse> getNearbyWarehouses(double ux, double uy, double maxDistance) {
        List<Warehouse> result = new ArrayList<>();
        for (Warehouse ds : warehouses) {
            double d = ds.distanceTo(ux, uy);
            if (d <= maxDistance) {
                result.add(ds);
            }
        }
        return result;
    }
}