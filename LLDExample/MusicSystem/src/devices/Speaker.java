package devices;

import models.*;

public class Speaker implements IAudioOutputDevice {

    public Speaker() {
        System.out.println("Connected to Speaker...");
    }

    @Override
    public void playAudio(Song song) {
        System.out.println("Playing "+ song.getName());
    }
}