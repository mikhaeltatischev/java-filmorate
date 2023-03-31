package ru.yandex.practicum.filmorate.exception.userException;

public class UserNotFoundException extends RuntimeException {

    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
