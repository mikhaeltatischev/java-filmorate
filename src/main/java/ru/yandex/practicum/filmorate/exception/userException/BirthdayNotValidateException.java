package ru.yandex.practicum.filmorate.exception.userException;

public class BirthdayNotValidateException extends Exception {

    private String message;

    public BirthdayNotValidateException() {
        message = "День рождения не может быть в будущем";
    }

    public String getMessage() {
        return message;
    }
}
