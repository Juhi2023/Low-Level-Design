package node;

import java.util.*;

public class Directory extends AbstractNode{
    
    Map<String, AbstractNode> children;

    public Directory(String name){
        super(name);
        children = new HashMap<>();
    }


    //extra method
    public void addNode(AbstractNode node) {
        children.put(node.getName(), node);
    }

    public List<AbstractNode> getChildren() {
        return new ArrayList<>(children.values());
    }

    public AbstractNode getNode(String name) {
        return children.get(name);
    }

    public AbstractNode removeNode(String name) {
        return children.remove(name);
    }


    //abstract method
    public int getSize(){
        int size=0;
        for(String x: children.keySet()){
            AbstractNode curr = children.get(x);
            size += curr.getSize();
        }
        return size;
    }

    public boolean isFile(){
        return false;
    }

    public void display(int depth){
        String indent = " ".repeat(depth * 2);
        for(String x: children.keySet()){
            AbstractNode curr = children.get(x);
            if(!(curr instanceof File)){
                System.out.println(indent + "--" + curr.getName());
            }
            curr.display(curr.isFile() ? depth : depth+1);
        }

    }
}