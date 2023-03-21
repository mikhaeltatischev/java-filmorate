package ru.yandex.practicum.filmorate.modelTest;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {


    @Test
    public void createUser() {
        User user = new User();
        user.setId(1);
        user.setLogin("login");
        user.setEmail("mail@mail.ru");
        user.setBirthday(LocalDate.of(1999, 02, 22));
        user.setName("name");

        assertTrue(user.isValidUser(user));
    }

    @Test
    public void createUserWithBadLogin() {
        User user = new User();
        user.setId(1);
        user.setLogin("lo g in");
        user.setEmail("mail@mail.ru");
        user.setBirthday(LocalDate.of(1999, 02, 22));
        user.setName("name");

        assertFalse(user.isValidUser(user));
    }

    @Test
    public void createUserWithBadEmail() {
        User user = new User();
        user.setId(1);
        user.setLogin("login");
        user.setEmail("mail mail.ru");
        user.setBirthday(LocalDate.of(1999, 02, 22));
        user.setName("name");

        assertFalse(user.isValidUser(user));
    }

    @Test
    public void createUserWithBadBirthday() {
        User user = new User();
        user.setId(1);
        user.setLogin("login");
        user.setEmail("mail@mail.ru");
        user.setBirthday(LocalDate.of(3000, 02, 22));
        user.setName("name");

        assertFalse(user.isValidUser(user));
    }
}
