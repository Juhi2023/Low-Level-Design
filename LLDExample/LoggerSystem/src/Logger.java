import enums.LogLevel;
import models.*;
import appender.*;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static final ConcurrentHashMap<String, Logger> instances = new ConcurrentHashMap<>();
    private LoggerConfig config;

    private Logger(LogLevel logLevel, LogAppender logAppender) {
        config = new LoggerConfig(logLevel, logAppender);
    }

    public static Logger getInstance(LogLevel logLevel, LogAppender logAppender) {
        String key = logLevel.name() + "_" + logAppender.getClass().getName();
        return instances.computeIfAbsent(key, k -> new Logger(logLevel, logAppender));
    }

    public void setConfig(LoggerConfig config) {
        synchronized (Logger.class) { 
            this.config = config;
        }
    }

    public void log(LogLevel level, String message) {
        
        if (level.getValue() >= config.getLogLevel().getValue()) {
            LogMessage logMessage = new LogMessage(level, message);
            config.getLogAppender().append(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}