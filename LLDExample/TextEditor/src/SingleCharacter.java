package texteditor;

public class SingleCharacter{
    private final char c;
    private final CharacterStyle style;


    public SingleCharacter(char c, CharacterStyle s){
        this.c = c;
        style = s;
    }
    
    public char render() {
        return c;
    }
}