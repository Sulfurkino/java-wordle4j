package ru.yandex.practicum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class WordleLogger {

    private final Path logFile = Paths.get("logs/app.log");

    public WordleLogger() {
        try {
            Files.createDirectories(logFile.getParent());
        } catch (IOException e){

        }
    }

    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        String logEntry = String.format("[%s] %s: %s%n", timestamp, level, message);

        try {
            Files.writeString(logFile, logEntry,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void error(String message) {
        log("ERROR", message);
    }
}