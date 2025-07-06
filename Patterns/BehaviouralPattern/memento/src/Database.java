import java.util.*;

//originator
public class Database{
    private HashMap<String, String> records = new HashMap<>();

    public void insert(String key, String value){
        records.put(key, value);
        System.out.println("Inserted "+key+": "+value);
    }

    public void update(String key, String value){
        if(records.containsKey(key)){
            records.put(key, value);
            System.out.println("Key Updated ");
        }
        System.out.println("Key not found.");
    }

    public void remove(String key){
        if(records.containsKey(key)){
            records.remove(key);
            System.out.println("Key Deleted.");
        }
        System.out.println("Key not found.");
    }

    public DatabaseMemeto createMemento(){
        System.out.println("Creating Memento....");
        return new DatabaseMemeto(new HashMap<>(records));
    }

    public void restoreMemento(DatabaseMemeto m){
        records = m.getState();
        System.out.println("Database restored from backup!");
    }

    public void display(){
        for(String x: records.keySet()){
            System.out.println(x+": "+records.get(x));
        }
    }
}