package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.controllerException.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.controllerException.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@Slf4j
public class UserController {

    private Set<User> users = new HashSet<>();

    @GetMapping("/users")
    public Set<User> getUsers() {
        log.info("Текущее количество пользователей: {}", users.size());

        return users;
    }

    @PutMapping(value = "/users")
    public User update(@Valid @RequestBody User user) throws UserNotFoundException {
        for (User currentUser : users) {
            if (currentUser.equals(user)) {
                currentUser.updateUser(user);
                log.info("Информация о обновленном пользователе: {}", currentUser);

                return currentUser;
            }
        }

        throw new UserNotFoundException();
    }

    @PostMapping(value = "/users")
    public User create(@Valid @RequestBody User user) {
        try {
            if (users.contains(user)) {
                throw new UserAlreadyExistException();
            } else {
                if (user.getName() == null) {
                    user.setName(user.getLogin());
                }

                User.increaseId();
                users.add(user);
                log.info("Информация о добавленном пользователе: {}", user);
            }
        } catch (UserAlreadyExistException e) {
            System.out.println(e.getMessage());
        }


        return user;
    }
}
