package handler;

import appender.*;
import enums.*;
import handler.*;
import models.*;


public class LogHandler{
    protected LogLevel level;
    protected LogHandler nextLogger;
    protected LogAppender appender;

    public LogHandler(LogLevel level, LogAppender appender){
        this.level = level;
        this.appender = appender;
    }

    public void setNextLogger(LogHandler nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogLevel level, String message) {
        if (this.level.getValue() >= level.getValue()) {
            LogMessage logMsg = new LogMessage(level, message);
            if (appender != null)
                appender.append(logMsg);
        }
        else if (nextLogger != null)
            nextLogger.logMessage(level, message);
    }
}