package components;

public class Player{
    String name;
    int position=1;

    public Player(String name){
        this.name=name;
    }


    public String getName(){
        return name;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int p){
        position=p;
    }
}