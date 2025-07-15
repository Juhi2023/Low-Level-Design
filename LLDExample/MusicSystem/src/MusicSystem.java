import models.*;
import manager.*;
import java.util.*;
import enums.*;
import devices.*;
import strategy.*;


class MusicSystem{
    Map<String, User> users;    // can also make user manager
    MusicLibrary library;
    AudioEngine engine;
    PlayStrategy playStrategy;
    Playlist loadedPlaylist=null;

    public MusicSystem(){
        users = new HashMap<>();
        library = MusicLibrary.getInstance();
        engine = new AudioEngine();
    }


    public User registerUser(String email, String password){
        User newuser = new User(email, password);
        users.put(email, newuser);
        return newuser;
    }

    public User getUser(String email, String password){
        return users.get(email);
    }
    
    public Artist addArtist(String name){
        Artist newArtist = new Artist(name);
        library.addArtist(newArtist);
        return newArtist;
    }

    public Album addAlbum(int artistId, String albumName){
        Artist a = library.getArtist(artistId);
        Album newAlbum = new Album(albumName, a);
        library.addAlbum(newAlbum);
        return newAlbum;
    }

    public Song addSongToAlbum(int albumId, String name, int duration){
        Album a = library.getAlbum(albumId); 
        Song newSong = new Song(name, a, duration);
        library.addSong(newSong);
        return newSong;
    }

    public void connectDevice(DeviceType deviceType){
        DeviceManager.getInstance().connect(deviceType);
    }

    public List<Song> searchSong(String s){
        return MusicLibrary.getInstance().searchSong(s);
    }

    public void play(int id){
        Song s = MusicLibrary.getInstance().getSong(id);
        engine.play(DeviceManager.getInstance().getOutputDevice(), s);
    }

    public void pause(){
        engine.pause();
    }


    public Playlist createPlaylist(String userEmail, String name){
        Playlist newPlaylist = new Playlist(name, users.get(userEmail));
        users.get(userEmail).createPlaylist(newPlaylist);
        return newPlaylist;
    }

    public void addSongToPlaylist(Playlist p, int songID){
        p.addSong(MusicLibrary.getInstance().getSong(songID));
    }


    public void setStrategy(PlayStrategy s){
        playStrategy=s;
    }


    public synchronized void loadPlaylist(Playlist p){
        if (playStrategy == null) {
            System.out.println("Play strategy not set before loading.");
            return;
        }
        loadedPlaylist=p;
        playStrategy.setPlaylist(p);
    }

    public void playAllTracks() {
        if (loadedPlaylist == null) {
            System.out.println("No playlist loaded.");
            return;
        }
        while (playStrategy.hasNext()) {
            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            engine.play(device, nextSong);
        }
        System.out.println("Completed playlist.");
    }

    public void playNextTrack() {
        if (loadedPlaylist == null) {
            System.out.println("No playlist loaded.");
        }
        if (playStrategy.hasNext()) {
            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            engine.play(device, nextSong);
        } else {
            System.out.println("Completed playlist");
        }
    }

    public void playPreviousTrack() {
        if (loadedPlaylist == null) {
            System.out.println("No playlist loaded.");
        }
        if (playStrategy.hasPrevious()) {
            Song prevSong = playStrategy.previous();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            engine.play(device, prevSong);
        } else {
            System.out.println("Completed playlist");
        }
    }

}