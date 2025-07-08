package setupstrategy;
import java.util.*;
import components.Board;
import components.boardentity.BoardEntity;

public class HumanSetupStrategy implements SetupStrategy{
    List<BoardEntity> entities;

    public HumanSetupStrategy(List<BoardEntity> entities){
        this.entities = entities;
    }
    
    public void setup(Board b){
        int size = b.getSize();
        for(BoardEntity x: entities){
            if(x.getStart()<1 || x.getStart()>size || x.getEnd()<1 || x.getEnd()>size ){
                System.out.println("Entities contains some invalid position. Please reenter.");
                return;
            }else{
                if(x.getName()=="LADDER"&&  x.getEnd()<= x.getStart() ){
                    System.out.println("Found Invalid position for ladder.");
                    return;

                }else if(x.getName()=="SNAKE"&&  x.getEnd()>=x.getStart() ){
                    System.out.println("Found Invalid position for snake.");
                    return;

                }
            }
        }

        for(BoardEntity x: entities){
            b.addEntity(x);
        }
    }
}