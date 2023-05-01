package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUserStorage().getUsers();
    }

    @PutMapping(value = "/users")
    public User update(@Valid @RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping(value = "/users")
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping(value = "/users/{id}/friends/{friendId}")
    public User addFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        return userService.addFriend(id, friendId);
    }

    @DeleteMapping(value = "/users/{id}/friends/{friendId}")
    public User deleteFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        return userService.deleteFriend(id, friendId);
    }

    @GetMapping(value = "/users/{id}/friends")
    public List<User> getFriends(@PathVariable("id") Long id) {
        return userService.getFriends(id);
    }

    @GetMapping(value = "/users/{id}/friends/common/{otherId}")
    public List<User> getMutualFriends(@PathVariable("id") Long id, @PathVariable("otherId") Long otherId) {
        return userService.getMutualFriends(id, otherId);
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserStorage().findUser(id);
    }
}
