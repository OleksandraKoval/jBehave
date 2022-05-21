package utils.logs;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    private static final Logger Log = LogManager.getLogger(Log.class);

    public static void info(String message) {
        Log.info(message);
    }
}
