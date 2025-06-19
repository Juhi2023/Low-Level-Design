package texteditor;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
import texteditor.SingleCharacter;

public class TextEditor{
    private CharacterStyleFactory factory;
    private List<List<SingleCharacter>> lines;
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public TextEditor(){
        factory = new CharacterStyleFactory();
        lines = new ArrayList<>();
        lines.add(new ArrayList<>());
    } 

    public void typeChar(char c, String font, int size, String color, boolean bold, boolean italic, int row, int col){
        SingleCharacter s = new SingleCharacter(c, factory.getCharacterStyle(font, size, color, bold, italic));
        Command cmd = new InsertCommand(lines, s, row, col);
        cmd.execute();
        undoStack.push(cmd);
        redoStack.clear();
    }

    public void deleteChar(int row, int col){
        if (row >= lines.size() || col >= lines.get(row).size()) return;
        Command cmd = new DeleteCommand(lines, row, col);
        cmd.execute();
        undoStack.push(cmd);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
        }
    }

    public void display() {
        for (List<SingleCharacter> line : lines) {
            for (SingleCharacter ch : line) {
                System.out.print(ch.render());
            }
            System.out.println();
        }
    }

}