package models;

import java.util.*;

public class Song{
    static int i=0;
    int id;
    String name;
    Album album;
    int duration;

    public Song(String name, Album album, int duration){
        this.name = name;
        this.id = ++i;
        this.album = album;
        this.duration = duration;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Album getAlbum(){
        return album;
    }
    
    public int getDuration(){
        return duration;
    }
}