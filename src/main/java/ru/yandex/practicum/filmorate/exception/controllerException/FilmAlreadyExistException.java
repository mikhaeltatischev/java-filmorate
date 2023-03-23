package ru.yandex.practicum.filmorate.exception.controllerException;

public class FilmAlreadyExistException extends RuntimeException {

    private String message;

    public FilmAlreadyExistException() {
        message = "Фильм уже существует";
    }

    public String getMessage() {
        return message;
    }
}
