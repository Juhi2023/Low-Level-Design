package appender;

import enums.LogLevel;
import models.LogMessage;


public interface LogAppender{
    void append(LogMessage msg);
}