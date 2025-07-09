package movementstrategy;

import components.Pair;

public class AIMovementStrategy implements MovementStrategy{
    @Override
    public Pair getNextPosition(Pair currHead, String direction){
        // write ai logic next position
        return currHead;
    }
}