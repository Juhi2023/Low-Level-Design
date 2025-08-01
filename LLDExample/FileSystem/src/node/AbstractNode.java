package node;

import java.util.*;

public abstract class AbstractNode{
    protected String name;
    protected Date createdAt;
    protected Date modifiedAt;
    protected boolean isFile;

    public AbstractNode(String name){
        this.name= name;
        this.createdAt = new Date();
        this.modifiedAt = new Date();
    }

    public String getName(){
        return name;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    protected void updateModifiedTime() {
        this.modifiedAt = new Date();
    }

    public abstract boolean isFile();
    public abstract int getSize();
    public abstract void display(int depth);
}