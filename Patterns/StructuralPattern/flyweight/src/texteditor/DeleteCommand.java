package texteditor;

import texteditor.Command;
import java.util.ArrayList;
import java.util.List;


public class DeleteCommand implements Command{
    private final List<List<SingleCharacter>> lines;
    private final int row, col;
    private SingleCharacter deleted;


    public DeleteCommand(List<List<SingleCharacter>> lines, int row, int col){
        this.lines = lines;
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute(){
        deleted = lines.get(row).remove(col);
    }

    @Override
    public void undo(){
        lines.get(row).add(col, deleted);
    }
}