import java.util.*;


class Main{
    public static void main(String args[]){
        Database db = new Database();
        TransitionManager t = new TransitionManager();
        t.beginTransaction(db);
        db.insert("Name", "Juhi");
        db.insert("Age", "23");
        t.commitTransaction();
        db.display();

        t.beginTransaction(db);
        db.insert("Name2", "Ram");
        db.insert("Age2", "25");
        db.display();

        //assume some error occur


        t.rollbackTransaction(db);
        db.display();
    }
}




//caretacker