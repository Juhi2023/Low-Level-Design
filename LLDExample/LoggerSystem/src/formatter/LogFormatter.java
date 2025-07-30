package formatter;

import models.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}