package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.filmException.*;
import ru.yandex.practicum.filmorate.exception.userException.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.userException.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.userException.UserNotValidateException;
import ru.yandex.practicum.filmorate.model.AppError;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleUserNotFound(final UserNotFoundException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleUserAlreadyExist(final UserAlreadyExistException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleFilmNotFound(final FilmNotFoundException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleFilmAlreadyExist(final FilmAlreadyExistException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleUserNotValidate(final UserNotValidateException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleFilmNotValidate(final FilmNotValidateException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleFilmNotValidate(final FilmNotLikedException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AppError handleUnauthorized(final Throwable e) {
        log.error("Произошла непредвиденная ошибка", e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleFilmNotValidate(final MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleMpaNotFound(final MpaNotFoundException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleGenreNotFound(final GenreNotFoundException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }
}
