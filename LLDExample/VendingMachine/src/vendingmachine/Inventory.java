package vendingmachine;
import java.util.*;

public class Inventory {
    private Map<String, Item> items = new HashMap<>();
    private Map<String, Integer> stock = new HashMap<>();

    public boolean isAvailable(String  code) {
        return stock.getOrDefault(code,0) > 0;
    }

    public boolean isFullyEmpty() {
        for(String s: stock.keySet()){
            if(stock.get(s)>0)
                return false;
        }
        return true;
    }
    
    public void addItem(Item i, int quantity) {
        String c = i.getCode();
        if(!items.containsKey(c)){
            items.put(c, i);
        }

        stock.put(c, stock.getOrDefault(c, 0)+ quantity);
    }

    public Item getItem(String code) {
        return items.get(code);
    }

    public void removeItem(String code){
        stock.put(code, stock.getOrDefault(code, 0)-1);
    }
}