package components;

import java.util.*;
import setupstrategy.SetupStrategy;
import components.boardentity.BoardEntity;


public class Board{
    private int size;
    List<BoardEntity> entities;
    Map<Integer, BoardEntity> map;

    public Board(int size){
        this.size = size*size;
        entities = new ArrayList<>();
        map = new HashMap<>();
    }

    public int getSize(){
        return size;
    }

    public void addEntity(BoardEntity e){
        if(!map.containsKey(e.getStart())){
            entities.add(e);
            map.put(e.getStart(), e);
        }else{
            System.out.println("Cell already have entity "+ e.getName());
        }
    }

    public void setupBoard(SetupStrategy s){
        s.setup(this);
    }

    public BoardEntity getEntity(int position) {
        return entities.get(position);
    }

    public int getNextPosition(int pos){
        if(map.containsKey(pos)){
            return map.get(pos).getEnd();
        }
        return pos;
    }
}