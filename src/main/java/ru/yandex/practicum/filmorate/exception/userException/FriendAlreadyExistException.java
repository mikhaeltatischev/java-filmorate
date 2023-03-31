package ru.yandex.practicum.filmorate.exception.userException;

public class FriendAlreadyExistException extends RuntimeException {

    public FriendAlreadyExistException(String message) {
        super(message);
    }
}
