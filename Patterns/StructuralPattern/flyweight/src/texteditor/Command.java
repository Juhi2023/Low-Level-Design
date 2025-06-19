package texteditor;

public interface Command {
    void execute();
    void undo();
}
