package manager;

import java.util.*;
import models.*;


public class MusicLibrary {
    private static MusicLibrary instance;
    private final Map<Integer, Song> songs;
    private final Map<Integer, Album> albums;
    private final Map<Integer, Artist> artists;

    private MusicLibrary() {
        songs = new HashMap<>();
        albums = new HashMap<>();
        artists = new HashMap<>();
    }

    public static synchronized MusicLibrary getInstance() {
        if (instance == null) {
            instance = new MusicLibrary();
        }
        return instance;
    }

    public void addSong(Song song) {
        songs.put(song.getID(), song);
    }

    public void addAlbum(Album album) {
        albums.put(album.getID(), album);
        for (Song song : album.getSongs()) {
            addSong(song);
        }
    }

    public void addArtist(Artist artist) {
        artists.put(artist.getID(), artist);
        for (Album album : artist.getAlbums()) {
            addAlbum(album);
        }
    }



    public Song getSong(int songId) {
        return songs.get(songId);
    }

    public List<Song> searchSong(String query) {
        List<Song> res = new ArrayList<>();
        for(Song song: new ArrayList<>(songs.values())){
            if (query.equals("") || song.getName().contains(query) || song.getAlbum().getArtist().getName().contains(query) || song.getAlbum().getName().contains(query)) {
                res.add(song);
            }
        }
        return res;
    }

    public Album getAlbum(int albumId) {
        return albums.get(albumId);
    }

    public Artist getArtist(int artistId) {
        return artists.get(artistId);
    }
}