package ru.yandex.practicum.exception;

public class InvalidDirectory extends RuntimeException {
    public InvalidDirectory(String message) {
        super(message);
    }
}
