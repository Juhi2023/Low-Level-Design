package models;

import java.util.*;

public class Playlist{
    static int i=0;
    int id;
    String name;
    User owner;
    List<Song> songs;

    public Playlist(String name, User user){
        this.name = name;
        this.id = ++i;
        songs = new ArrayList<>();
        this.owner = user;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public User getOwner(){
        return owner;
    }
    
    public List<Song> getSongs(){
        return songs;
    }

    public void addSong(Song a){
        songs.add(a);
    }
}