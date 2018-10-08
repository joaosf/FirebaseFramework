package Singleton;

import java.util.HashMap;

public class FrameworkLogger {
    private static FrameworkLogger ourInstance = new FrameworkLogger();
    private static HashMap<String,String> logList = new HashMap<>();

    public static FrameworkLogger getInstance() {
        return ourInstance;
    }

    private FrameworkLogger() {
    }

    public void addLog(String titleLog,String logString) {
        logList.put(titleLog,logString);
    }

    public HashMap<String,String> getLogList() {
        return logList;
    }
}
