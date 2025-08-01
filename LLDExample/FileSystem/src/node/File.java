package node;

import java.util.*;

public class File extends AbstractNode{
    
    int size;
    String content;

    public File(String name){
        super(name);
        size=0;
        content="";
    }


    //abstract method
    public int getSize(){
        return size;
    }

    public boolean isFile(){
        return true;
    }

    public void display(int depth){
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "*" + name);
    }



    //extra method
    public String getContent(){
        return content;
    }

    public void appendContent(String s){
        content += s;
        size = content.length();
    }
}