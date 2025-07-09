package movementstrategy;

import components.Pair;

public interface MovementStrategy{
    Pair getNextPosition(Pair currHead, String direction);
}