package server;

import java.io.FileInputStream;
import java.util.logging.LogManager;

public class StartServer {
    public static void main(String[] args) {
        try {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(new FileInputStream("logging.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Server();
    }
}
