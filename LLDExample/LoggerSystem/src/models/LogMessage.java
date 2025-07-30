package models;

import java.util.*;
import enums.LogLevel;

public class LogMessage{
    private LogLevel level;
    private String message;
    private long timeStamp;

    public LogMessage(LogLevel level, String message){
        this.level = level;
        this.message = message;
        this.timeStamp = new Date().getTime();
    }

    public LogLevel getLevel(){
        return level;
    }

    public String getMessage(){
        return message;
    }

    public long getTimestamp() {
        return timeStamp;
    }

    public String toString(){
        return "[" + level + "]" + timeStamp + " - " + message;
    }
    
}