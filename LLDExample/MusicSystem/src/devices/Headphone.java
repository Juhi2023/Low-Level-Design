package devices;

import models.*;

public class Headphone implements IAudioOutputDevice {

    public Headphone() {
        System.out.println("Connected to Headphone...");
    }

    @Override
    public void playAudio(Song song) {
        System.out.println("Playing "+ song.getName());
    }
}