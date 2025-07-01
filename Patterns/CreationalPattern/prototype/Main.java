class Main{
    public static void main(String args[]){
        NCP n1 = new NCP("Juhi", 30, 5, 3);
        n1.view();
        NCP n2 = (NCP) n1.clone();
        n2.view();
    }
}

interface Cloneable{
    Cloneable clone();
}

public class NCP implements Cloneable{
    String name;
    int health;
    int attack;
    int defense;

    public NCP(String name, int health, int attack, int defense){
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        System.out.println("Setting up...");
    }

    public NCP(NCP obj){
        this.name = obj.name;
        this.health = obj.health;
        this.attack = obj.attack;
        this.defense = obj.defense;
        System.out.println("Cloning...");
    }

    public Cloneable clone(){
        return new NCP(this);
    }

    public void setName(String name){
        this.name = name;
    }

    public void view(){
        System.out.println(name+": ");
        System.out.println("Health: "+ health);
        System.out.println("Attack: "+ attack);
        System.out.println("Defense: "+ defense);
    }
}