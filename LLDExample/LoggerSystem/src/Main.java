import formatter.*;
import enums.LogLevel;
import appender.*;
import models.*;
import handler.*;

public class Main{
    public static void main(String args[]){
        LogAppender consoleAppender = new ConsoleAppender(new SimpleFormatter());
        LogAppender fileAppender = new FileAppender("logs.txt", new SimpleFormatter());


        // using COR handler
        // Select the log appender (console or file)
        LogHandler loggerChain = new LogHandler(LogLevel.INFO, consoleAppender);
        LogHandler errorLogger = new LogHandler(LogLevel.ERROR, consoleAppender);
        LogHandler debugLogger = new LogHandler(LogLevel.DEBUG, consoleAppender);
        loggerChain.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);

        // Use a single logging approach to avoid duplication
        System.out.println("Logging INFO level message:");
        loggerChain.logMessage(LogLevel.INFO, "This is an information.");
        System.out.println("nLogging DEBUG level message:");
        loggerChain.logMessage(LogLevel.DEBUG, "This is a debug level information.");
        System.out.println("nLogging ERROR level message:");
        loggerChain.logMessage(LogLevel.ERROR, "This is an error information.");

        // Demonstrate the singleton Logger usage as an alternative
        System.out.println("\nUsing Singleton Logger:");
        Logger logger = Logger.getInstance(LogLevel.INFO, consoleAppender);
        logger.setConfig(new LoggerConfig(LogLevel.INFO, fileAppender));
        logger.error("Using singleton Logger - Error message");
        logger.info("Using singleton Logger - Info message");
        logger.debug("Using singleton Logger - Debug message");
    }
}