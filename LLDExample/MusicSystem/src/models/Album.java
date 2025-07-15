package models;

import java.util.*;

public class Album{
    static int i=0;
    int id;
    String name;
    Artist artist;
    List<Song> songs;

    public Album(String name, Artist artist){
        this.name = name;
        this.id = ++i;
        songs = new ArrayList<>();
        this.artist = artist;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Artist getArtist(){
        return artist;
    }
    
    public List<Song> getSongs(){
        return songs;
    }

    public void addSong(Song a){
        songs.add(a);
    }
}