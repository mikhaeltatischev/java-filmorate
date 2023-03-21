package ru.yandex.practicum.filmorate.exception.filmException;

public class DurationNotValidateException extends Exception {

    private String message;

    public DurationNotValidateException() {
        message = "Продолжительность не может быть отрицательной";
    }

    public String getMessage() {
        return message;
    }
}
