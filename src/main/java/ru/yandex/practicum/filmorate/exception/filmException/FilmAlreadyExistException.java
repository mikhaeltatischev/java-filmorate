package ru.yandex.practicum.filmorate.exception.filmException;

public class FilmAlreadyExistException extends RuntimeException {

    private String message;

    public FilmAlreadyExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
