package strategy;

import models.*;

public interface PlayStrategy {
    void setPlaylist(Playlist playlist);
    
    Song next();
    boolean hasNext();

    Song previous();
    boolean hasPrevious();
}