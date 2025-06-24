package models;

import java.util.*;

public class Menu {
    private List<MenuItem> dishes;

    public Menu() {
        dishes = new ArrayList<>();
    }

    public List<MenuItem> getMenu() {
        return dishes;
    }

    public void clear() {
        dishes.clear();
    }

    public void add(MenuItem i){
        dishes.add(i);
    }
}
