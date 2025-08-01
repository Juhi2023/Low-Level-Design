import java.util.*;
import node.*;

public class FileSystem{
    private static FileSystem instance;

    private AbstractNode root;

    private FileSystem(){
        root = new Directory("root");
    }

    public static FileSystem getInstance(){
        if(instance ==null){
            instance = new FileSystem();
        }
        return instance;
    }


    //create directory
    public boolean mkdir(String path){
        if(path==null || path.isEmpty() || !path.startsWith("/")){
            System.out.println("Invalid Path.");
            return false;
        }
        String [] parts = path.split("/");
        Directory  curr = (Directory)root;
        for(int i=1; i<parts.length; i++){
            if(curr.getNode(parts[i])==null){
                curr.addNode(new Directory(parts[i]));
            }else if(curr.getNode(parts[i]) instanceof File){
                System.out.println("Can't create directory in this path.");
                return false;
            }
            curr = (Directory) curr.getNode(parts[i]);
        }
        
        return false;
    }

    //create File
    public boolean createFile(String path, String name){
        if(path==null || path.isEmpty() || !path.startsWith("/")){
            System.out.println("Invalid Path.");
            return false;
        }

        String [] parts = path.split("/");

        Directory  curr = (Directory)root;
        for(int i=1; i<parts.length; i++){
            if(curr.getNode(parts[i]) instanceof File){
                System.out.println("Path not found.");
                return false;
            }
            curr= (Directory) curr.getNode(parts[i]);
        }

        if (curr.getNode(name) !=null) {
            System.out.println("Directory already exist with same name.");
            return false;

        }
        curr.addNode(new File(name));        
        return true;
    }

    //delete file
    public boolean deletePath(String path){
        AbstractNode node = getNode(path.substring(0, path.lastIndexOf('/')));
        // System.out.println(node.getName());
        if(node==null || node.isFile()){
            return false;
        }
            // System.out.println(path.substring(0, path.lastIndexOf('/')));
        

        ((Directory) node).removeNode(path.substring(path.lastIndexOf('/')+1));
        return true;
    }

    //display entire structure
    public void display(){
        root.display(0);
    }

    public AbstractNode getNode(String path){
        String [] parts = path.split("/");
        AbstractNode  curr = root;

        for(int i=1; i<parts.length; i++){

            if (curr.isFile() || ((Directory)curr).getNode(parts[i]) == null) {
                return null;
            }
            curr = ((Directory) curr).getNode(parts[i]);
        }
        return curr;
    }

    public boolean addContent(String path, String content){
        AbstractNode node = getNode(path);
        if(node==null || !node.isFile()){
            return false;
        }

        File file = (File) node;
        file.appendContent(content);
        return true;
    }


    public boolean getContent(String path){
        AbstractNode node = getNode(path);
        if(node==null || !node.isFile()){
            return false;
        }

        File file = (File) node;
        System.out.println(file.getContent());
        return true;
    }


}
