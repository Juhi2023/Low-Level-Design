package texteditor;

import texteditor.Command;
import java.util.ArrayList;
import java.util.List;

public class InsertCommand implements Command{
    private final List<List<SingleCharacter>> lines;
    private final int row, col;
    private SingleCharacter character;


    public InsertCommand(List<List<SingleCharacter>> lines, SingleCharacter s, int row, int col){
        this.lines = lines;
        this.row = row;
        this.col = col;
        this.character = s;
    }

    @Override
    public void execute(){
        while (lines.size() <= row) {
            lines.add(new ArrayList<>());
        }
        lines.get(row).add(col, character);
    }

    @Override
    public void undo(){
        lines.get(row).remove(col);
    }
}