package ru.yandex.practicum.filmorate.exception.userException;

public class ValidationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Ошибка валидации";
    }
}
