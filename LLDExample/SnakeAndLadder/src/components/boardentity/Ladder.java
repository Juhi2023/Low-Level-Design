package components.boardentity;

public class Ladder extends BoardEntity{
    public Ladder(int s, int e){
        super(s,e, "LADDER");
    }

    public void display(){
        System.out.println("Ladder position: "+start+", "+end);
    }
}