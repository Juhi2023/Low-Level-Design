package devices;

import models.*;

public class Bluetooth implements IAudioOutputDevice {

    public Bluetooth() {
        System.out.println("Connected to Bluetooth...");
    }

    @Override
    public void playAudio(Song song) {
        System.out.println("Playing "+ song.getName());
    }
}