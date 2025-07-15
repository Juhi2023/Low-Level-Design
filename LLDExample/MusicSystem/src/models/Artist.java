package models;

import java.util.*;


public class Artist{
    static int i=0;
    int id;
    String name;
    List<Album> albums;

    public Artist(String name){
        this.name = name;
        this.id = ++i;
        albums = new ArrayList<>();
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public List<Album> getAlbums(){
        return albums;
    }

    public void addAlbum(Album a){
        albums.add(a);
    }
}