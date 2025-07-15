import models.*;
import enums.*;
import strategy.*;

class Main{
    public static void main(String args[]){
        MusicSystem musicsystem = new MusicSystem();

        User user1 = musicsystem.registerUser("juhi@gmail.com", "Juhi123");
        User user2 = musicsystem.registerUser("vansh@gmail.com", "Vansh123");

        Artist artist1 = musicsystem.addArtist("Selena");
        Artist artist2 = musicsystem.addArtist("Arijit");

        Album album1 = musicsystem.addAlbum(artist1.getID(), "Album 1");
        Album album2 = musicsystem.addAlbum(artist2.getID(), "Album 1");

        Song song1 = musicsystem.addSongToAlbum(album1.getID(), "Expresso", 30);
        Song song2 = musicsystem.addSongToAlbum(album2.getID(), "With you", 30);

        musicsystem.connectDevice(DeviceType.BLUETOOTH);
        System.out.println();

        musicsystem.play(song2.getID());
        musicsystem.pause();
        System.out.println();


        int i=1;
        for(Song s: musicsystem.searchSong("")){
            System.out.println(i++ +". "+s.getName());
        }
        System.out.println();


        Playlist user1Playlist = musicsystem.createPlaylist("juhi@gmail.com", "Hip-hop");
        musicsystem.addSongToPlaylist(user1Playlist, song1.getID());
        musicsystem.addSongToPlaylist(user1Playlist, song2.getID());


        //Sequential play of playlist
        musicsystem.setStrategy(new SequentialPlayStrategy());
        musicsystem.loadPlaylist(user1Playlist);
        // musicsystem.playAllTracks();
        musicsystem.playNextTrack();
        musicsystem.playNextTrack();
    }
}