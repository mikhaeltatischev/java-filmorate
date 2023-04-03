package ru.yandex.practicum.filmorate.exception.userException;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
