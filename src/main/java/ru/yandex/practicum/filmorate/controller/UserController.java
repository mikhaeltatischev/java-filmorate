package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.controllerException.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.controllerException.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    private static int id = 1;
    private Map<Integer, User> users = new HashMap<>();

    @GetMapping("/users")
    public List<User> getUsers() {
        log.info("Текущее количество пользователей: {}", users.size());

        return getUsers(users);
    }

    @PutMapping(value = "/users")
    public User update(@Valid @RequestBody User user) throws UserNotFoundException {
        if (users.containsKey(user.getId())) {
            user.updateUser(user);
            users.put(user.getId(), user);
            log.info("Информация о обновленном пользователе: {}", user);

            return user;
        }

        throw new UserNotFoundException();
    }

    @PostMapping(value = "/users")
    public User create(@Valid @RequestBody User user) {
        if (users.containsKey(user.getId())) {
            throw new UserAlreadyExistException();
        } else {
            if (user.getName() == null) {
                user.setName(user.getLogin());
            }

            user.setId(id);
            id++;
            users.put(user.getId(), user);
            log.info("Информация о добавленном пользователе: {}", user);
        }

        return user;
    }

    private List<User> getUsers(Map<Integer, User> usersMap) {
        List<User> users = new ArrayList<>();

        for (User user : usersMap.values()) {
            users.add(user);
        }

        return users;
    }
}
