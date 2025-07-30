package appender;

import enums.LogLevel;
import models.LogMessage;
import formatter.LogFormatter;


public class ConsoleAppender implements LogAppender{
    private LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }
    public void append(LogMessage msg){
        System.out.println(formatter.format(msg));
    }
}