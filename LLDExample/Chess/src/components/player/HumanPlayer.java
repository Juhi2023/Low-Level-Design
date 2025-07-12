package components.player;

import components.*;
import enums.*;
import java.util.*;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, Color color) {
        super(name, color);
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public Position[] makeMove(Board board){
        System.out.println("Enter From position: ");
        Scanner sc = new Scanner(System.in);
        int row =  sc.nextInt();
        int col = sc.nextInt();

        System.out.println("Enter To position: ");
        int row1 =  sc.nextInt();
        int col1 = sc.nextInt();

        return new Position[]{new Position(row, col), new Position(row1, col1)};
    }
}