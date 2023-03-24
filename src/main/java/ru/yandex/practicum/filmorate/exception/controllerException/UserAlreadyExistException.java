package ru.yandex.practicum.filmorate.exception.controllerException;

public class UserAlreadyExistException extends RuntimeException {

    private String message;

    public UserAlreadyExistException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
