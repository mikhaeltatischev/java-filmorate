package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.user.User;

import java.util.List;

public interface UserStorage {

    List<User> getUsers();

    User create(User user);

    User update(User user);

    User findUser(Long id);

    void addFriend(Long id, Long friendId);

    void deleteFriend(Long id, Long friendId);
}
