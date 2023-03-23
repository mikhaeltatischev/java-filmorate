package ru.yandex.practicum.filmorate.exception.controllerException;

public class UserAlreadyExistException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Пользователь уже существует";
    }
}
