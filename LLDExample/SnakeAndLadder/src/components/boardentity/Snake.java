package components.boardentity;

public class Snake extends BoardEntity{
    public Snake(int s, int e){
        super(s,e, "SNAKE");
    }

    public void display(){
        System.out.println("Snake position: "+start+", "+end);
    }
}