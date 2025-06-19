package texteditor;

public class CharacterStyle{
    private final String fontFamily;
    private final int fontSize;
    private final String color;
    private final boolean bold;
    private final boolean italic;

    public CharacterStyle(String font, int size, String color, boolean bold, boolean italic){
        fontFamily=font;
        fontSize=size;
        this.color = color;
        this.bold = bold;
        this.italic = italic;
    }
}