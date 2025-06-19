//DESIGN
// ✅ Text insertion and deletion
// ✅ Cursor movement
// ✅ Extensible text formatting (bold, italic, font, etc.)
// ✅ Undo/redo functionality (command pattern)
// ✅ Flyweight memory sharing

package texteditor;

import texteditor.TextEditor;

public class Main{
    public static void main(String args[]){
        TextEditor editor = new TextEditor();
        editor.typeChar('a', "Times Roman", 13, "Red", true, false, 0,0);
        editor.typeChar('b', "Times Roman", 13, "Red", true, false, 0,1);
        editor.typeChar('c', "Times Roman", 13, "Red", true, false, 1,0);
        editor.display();

        editor.deleteChar(0, 1);
        editor.display();

        editor.undo();
        editor.display();
    }
}

// in flyweight folder
// javac -d out src/texteditor/*.java
// java -cp out texteditor.Main