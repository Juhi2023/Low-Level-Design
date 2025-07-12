package components.player;

import enums.Color;
import components.*;

public abstract class Player {
    protected String name;
    protected Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public abstract Position[] makeMove(Board board);
}