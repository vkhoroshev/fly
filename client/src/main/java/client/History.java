package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class History {
    private static PrintWriter printWriter;

    private static String getFilename(String login) {
        return String.format("history/history_%s.txt", login);
    }

    public static void startRecording(String login) {
        try {
            printWriter = new PrintWriter(new FileOutputStream(getFilename(login), true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void stopRecording() {
        if (printWriter != null) {
            printWriter.close();
        }
    }

    public static void writeMessage(String message) {
        printWriter.println(message);
    }

    public static String getLastMessages(String login, int count) {
        if (!Files.exists(Paths.get(getFilename(login)))) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> messageHistory = Files.readAllLines(Paths.get(getFilename(login)));
            int sizeHistory = messageHistory.size();
            int startPosition = Math.max(sizeHistory - count, 0);

            for (int i = startPosition; i < sizeHistory; i++) {
                stringBuilder.append(messageHistory.get(i)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
