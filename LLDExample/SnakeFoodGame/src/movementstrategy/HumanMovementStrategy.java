package movementstrategy;

import components.Pair;

public class HumanMovementStrategy implements MovementStrategy{
    @Override
    public Pair getNextPosition(Pair currHead, String direction){
        int row = currHead.getRow();
        int col = currHead.getCol();	        
        switch (direction) {
            case "U": return new Pair(row - 1, col);
            case "D": return new Pair(row + 1, col);
            case "L": return new Pair(row, col - 1);
            case "R": return new Pair(row, col + 1);
            default: return currHead;
        }
    }
}