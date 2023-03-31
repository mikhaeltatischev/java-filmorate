package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.userException.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.userException.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {

    private static Long id = 1L;
    private Map<Long, User> users = new HashMap<>();

    @Override
    public List<User> getUsers() {
        List<User> listOfUsers = new ArrayList<>();

        for (User user : users.values()) {
            listOfUsers.add(user);
        }

        log.info("Текущее количество пользователей: {}", listOfUsers.size());

        return listOfUsers;
    }

    @Override
    public User create(User user) {
        if (!users.containsValue(user)) {
            if (user.getName() == null || user.getName().isBlank()) {
                user.setName(user.getLogin());
            }

            if (user.getId() == null) {
                user.setId(id);
                id++;
            }
            users.put(user.getId(), user);
            log.info("Информация о добавленном пользователе: {}", user);

            return user;
        }
        throw new UserAlreadyExistException("User with id " + user.getId() + " already exist");
    }

    @Override
    public User update(User user) {
        if (findUser(user) != null) {
            user.updateUser(user);
            users.put(user.getId(), user);
            log.info("Информация о обновленном пользователе: {}", user);

            return user;
        }
        throw new UserNotFoundException("User with id " + user.getId() + " not found");
    }

    @Override
    public User findUser(User user) {
        if (users.get(user.getId()) == null) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }

        return users.get(user.getId());
    }

    @Override
    public User findUser(Long id) {
        if (users.get(id) == null) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }

        return users.get(id);
    }
}
