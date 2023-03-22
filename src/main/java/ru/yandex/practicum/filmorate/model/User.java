package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.userException.BirthdayNotValidateException;
import ru.yandex.practicum.filmorate.exception.userException.EmailNotValidateException;
import ru.yandex.practicum.filmorate.exception.userException.LoginNotValidateException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = {"id"})
@Slf4j
public class User {

    private static int inc = 1;
    private int id = inc;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\S*$")
    private String login;
    private String name;
    @Past
    private LocalDate birthday;

    public static void increaseId() {
        inc++;
    }

    public User updateUser(User user) {
        if (isValidEmail(user.getEmail())) {
            this.setEmail(user.getEmail());
        }
        if (isValidLogin(user.getLogin())) {
            this.setLogin(user.getLogin());
        }
        if (isValidName(user.getName())) {
            this.setName(user.getName());
        }
        if (user.isValidBirthday(user.getBirthday())) {
            this.setBirthday(user.getBirthday());
        }

        return this;
    }

    public boolean isValidUser(User user) {
        if (isValidEmail(user.getEmail()) && isValidLogin(user.getLogin()) && isValidBirthday(user.getBirthday())) {
            return true;
        }

        return false;
    }

    private boolean isValidEmail(String email) {
        try {
            if (!email.contains("@") || email.isBlank()) {
                throw new EmailNotValidateException();
            }
        } catch (EmailNotValidateException e) {
            log.info(e.getMessage());

            return false;
        }

        return true;
    }

    private boolean isValidLogin(String login) {
        try {
            if (login.contains(" ") || login.isBlank()) {
                throw new LoginNotValidateException();
            }
        } catch (LoginNotValidateException e) {
            log.info(e.getMessage());

            return false;
        }

        return true;
    }

    private boolean isValidBirthday(LocalDate birthday) {
        try {
            if (birthday == null || birthday.isAfter(LocalDate.now())) {
                throw new BirthdayNotValidateException();
            }
        } catch (BirthdayNotValidateException e) {
            log.info(e.getMessage());

            return false;
        }

        return true;
    }

    private boolean isValidName(String name) {
        if (name == null || name.isBlank()) {

            return false;
        }

        return true;
    }
}
