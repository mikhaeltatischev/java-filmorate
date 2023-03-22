package ru.yandex.practicum.filmorate.exception.userException;

public class ValidationException extends Exception{

    @Override
    public String getMessage() {
        return "Ошибка валидации";
    }
}
