package texteditor;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import texteditor.CharacterStyle;

public class CharacterStyleFactory{
    Map<String, CharacterStyle> map;

    public CharacterStyleFactory(){
        map = new HashMap<>();
    }

    public CharacterStyle getCharacterStyle(String font, int size, String color, boolean bold, boolean italic){
        String key = "" + Objects.hash(font, size, color, bold, italic);
        if (!map.containsKey(key)) {
            CharacterStyle ch = new CharacterStyle(font, size, color, bold, italic);
            map.put(key, ch);
        }
        return map.get(key);
    }

}