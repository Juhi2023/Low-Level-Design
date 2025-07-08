package components.boardentity;

public abstract class BoardEntity{
    String name;
    int start;
    int end;

    public BoardEntity(int s, int e, String name){
        start=s;
        end=e;
        this.name=name;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    public String getName(){
        return name;
    }

    public abstract void display();
}