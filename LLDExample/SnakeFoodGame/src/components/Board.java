package components;

public class Board{
    private int height;
    private int width;

    private static Board instance;

    private Board(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static Board getInstance(int w, int h){
        if(instance==null){
            return new Board(w, h);
        }
        return instance;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}