package formatter;

import models.LogMessage;

public class SimpleFormatter implements LogFormatter {
    public String format(LogMessage message){
        return "[" + message.getTimestamp() + "] " + "[" + message.getLevel() + "] " + "[" + message.getMessage() + "]";
    }
}