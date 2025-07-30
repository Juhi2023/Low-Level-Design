package appender;

import java.util.*;
import java.io.*;
import enums.LogLevel;
import models.LogMessage;
import formatter.LogFormatter;

public class FileAppender implements LogAppender{
    private LogFormatter formatter;
    private String path;

    public FileAppender(String path, LogFormatter formatter) {
        this.path = path;
        this.formatter = formatter;
    }
    public void append(LogMessage msg){
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(formatter.format(msg)+ "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
        }
    }
}