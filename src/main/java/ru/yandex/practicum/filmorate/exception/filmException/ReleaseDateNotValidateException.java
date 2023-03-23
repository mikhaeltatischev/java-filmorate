package ru.yandex.practicum.filmorate.exception.filmException;

public class ReleaseDateNotValidateException extends RuntimeException {

    private String message;

    public ReleaseDateNotValidateException() {
        message = "Дата релиза не должна быть раньше чем 28 декабря 1895 года";
    }

    public String getMessage() {
        return message;
    }
}
