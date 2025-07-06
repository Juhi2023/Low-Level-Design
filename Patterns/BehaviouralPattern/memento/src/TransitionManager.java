
public class TransitionManager{
    DatabaseMemeto backup;

    public void beginTransaction(Database d){
        System.out.println("------Begin Transaction-------");
        backup = d.createMemento();
    }

    public void commitTransaction(){
        System.out.println("------Commit Transaction-------");
        backup = null;
        System.out.println("Transaction commited successfully.");
    }

    public void rollbackTransaction(Database d){
        System.out.println("------Rollback Transaction-------");
        if(backup!=null){
            d.restoreMemento(backup);
            backup=null;
            System.out.println("Transaction rolled back successfully.");
        }else{
            System.out.println("No data available for rollback.");
        }
    }

}