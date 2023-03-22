package ru.yandex.practicum.filmorate.exception.filmException;

public class NameNotValidateException extends Exception {

    private String message;

    public NameNotValidateException() {
        message = "Имя не может быть пустым";
    }

    public String getMessage() {
        return message;
    }
}
