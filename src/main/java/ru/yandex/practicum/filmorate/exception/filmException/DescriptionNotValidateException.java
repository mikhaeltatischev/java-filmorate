package ru.yandex.practicum.filmorate.exception.filmException;

public class DescriptionNotValidateException extends RuntimeException {

    private String message;

    public DescriptionNotValidateException() {
        message = "Описание не должно быть длинее 200 символов";
    }

    public String getMessage() {
        return message;
    }
}
