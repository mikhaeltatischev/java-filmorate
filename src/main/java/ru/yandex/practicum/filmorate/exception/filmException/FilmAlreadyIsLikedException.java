package ru.yandex.practicum.filmorate.exception.filmException;

public class FilmAlreadyIsLikedException extends RuntimeException {

    public FilmAlreadyIsLikedException(String message) {
        super(message);
    }
}
