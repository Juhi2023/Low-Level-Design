import java.util.*;
import node.*;

public class Main{
    public static void main(String args[]){
        FileSystem system = FileSystem.getInstance();
        system.mkdir("/a/b/c/d");
        system.createFile("/a/b", "text.txt");
        system.createFile("/a", "text2.txt");
        system.mkdir("/a/e/f");
        system.createFile("/a/b/c", "text3.txt");
        system.addContent("/a/text2.txt", "Hii");

        system.getContent("/a/text2.txt");

        system.display();
        

        system.deletePath("/a/e/f");
        system.deletePath("/a/text2.txt");
        
        system.display();
    }
}



