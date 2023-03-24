package ru.yandex.practicum.filmorate.exception.controllerException;

public class FilmNotFoundException extends RuntimeException {

    private String message;

    public FilmNotFoundException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
