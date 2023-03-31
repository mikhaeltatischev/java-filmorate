package ru.yandex.practicum.filmorate.exception.filmException;

public class FilmAlreadyExistException extends RuntimeException {

    public FilmAlreadyExistException(String message) {
        super(message);
    }

}
