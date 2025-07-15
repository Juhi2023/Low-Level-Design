package factory;

import devices.*;
import enums.*;

public class DeviceFactory{
    public static IAudioOutputDevice createDevice(DeviceType deviceType) {
        switch (deviceType) {
            case BLUETOOTH:
                return new Bluetooth();
            case HEADPHONE:
                return new Headphone();
            case SPEAKER:
            default:
                return new Speaker();
        }
    }
}

