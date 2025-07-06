import java.util.*;

//memento
class DatabaseMemeto{
    HashMap<String, String> data = new HashMap<>();

    public DatabaseMemeto(HashMap<String, String> d){
        data=d;
    }

    public HashMap<String, String> getState(){
        return data;
    }
}
