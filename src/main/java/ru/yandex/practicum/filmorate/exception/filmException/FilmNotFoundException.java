package ru.yandex.practicum.filmorate.exception.filmException;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(String message) {
        super(message);
    }
}
