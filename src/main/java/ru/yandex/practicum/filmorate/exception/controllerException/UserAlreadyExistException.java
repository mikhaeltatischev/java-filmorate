package ru.yandex.practicum.filmorate.exception.controllerException;

public class UserAlreadyExistException extends Exception {

    @Override
    public String getMessage() {
        return "Пользователь уже существует";
    }
}
