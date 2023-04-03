package ru.yandex.practicum.filmorate.exception.userException;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
