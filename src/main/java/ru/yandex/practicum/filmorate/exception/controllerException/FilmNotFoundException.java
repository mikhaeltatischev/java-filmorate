package ru.yandex.practicum.filmorate.exception.controllerException;

public class FilmNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Фильм не найден";
    }
}
