package ru.yandex.practicum.exception;

public class LoadException extends RuntimeException {
    public LoadException(String message) {
        super(message);
    }
}
