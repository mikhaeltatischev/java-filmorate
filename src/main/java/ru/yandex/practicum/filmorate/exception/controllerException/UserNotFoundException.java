package ru.yandex.practicum.filmorate.exception.controllerException;

public class UserNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Пользователь не найден";
    }
}
