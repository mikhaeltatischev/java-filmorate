package ru.yandex.practicum.filmorate.exception.filmException;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
