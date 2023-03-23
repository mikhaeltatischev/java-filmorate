package ru.yandex.practicum.filmorate.exception.filmException;

public class NameNotValidateException extends RuntimeException {

    private String message;

    public NameNotValidateException() {
        message = "Имя не может быть пустым";
    }

    public String getMessage() {
        return message;
    }
}
