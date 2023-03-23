package ru.yandex.practicum.filmorate.exception.userException;

public class EmailNotValidateException extends RuntimeException {

    private String message;

    public EmailNotValidateException() {
        message = "Электронная почта пустая или не содержит символ \"@\"";
    }

    public String getMessage() {
        return message;
    }
}
