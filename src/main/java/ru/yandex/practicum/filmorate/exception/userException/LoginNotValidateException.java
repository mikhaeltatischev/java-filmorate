package ru.yandex.practicum.filmorate.exception.userException;

public class LoginNotValidateException extends RuntimeException {

    private String message;

    public LoginNotValidateException() {
        message = "Логин пустой или содержит пробел";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
