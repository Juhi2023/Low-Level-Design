package models;

import java.util.*;

public class User{
    static int i=0;
    int id;
    String email;
    String password;
    Map<Integer, Playlist> playlists;

    public User(String email, String password){
        this.id = ++i;
        this.email = email;
        this.password = password;
        playlists = new HashMap<>();
    }

    public int getID(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public List<Playlist> getPlaylists(){
        return new ArrayList<>(playlists.values());
    }

    public Playlist getPlaylist(int id){
        return playlists.get(id);
    }

    public void createPlaylist(Playlist p){
        playlists.put(p.getID(), p);
    }
}