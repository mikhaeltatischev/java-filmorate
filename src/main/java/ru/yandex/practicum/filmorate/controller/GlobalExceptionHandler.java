package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.filmException.FilmAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotLikedException;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotValidateException;
import ru.yandex.practicum.filmorate.exception.userException.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.userException.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.userException.UserNotValidateException;
import ru.yandex.practicum.filmorate.model.AppError;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleUserNotFound(UserNotFoundException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleUserAlreadyExist(UserAlreadyExistException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleFilmNotFound(FilmNotFoundException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleFilmAlreadyExist(FilmAlreadyExistException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleUserNotValidate(UserNotValidateException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleFilmNotValidate(FilmNotValidateException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleFilmNotValidate(FilmNotLikedException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }


}
