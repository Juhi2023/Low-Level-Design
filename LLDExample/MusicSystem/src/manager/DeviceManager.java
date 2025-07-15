package manager;

import devices.*;
import factory.*;
import enums.*;

public class DeviceManager{
    private static DeviceManager instance=null;
    private IAudioOutputDevice currentOutputDevice;

    private DeviceManager(){
        currentOutputDevice=null;
    }

    public static DeviceManager getInstance(){
        if(instance==null){
            instance = new DeviceManager();
        }
        return instance;
    }

    public void connect(DeviceType deviceType){
        currentOutputDevice = DeviceFactory.createDevice(deviceType);
    }

    public IAudioOutputDevice getOutputDevice(){
        return currentOutputDevice;
    }
}