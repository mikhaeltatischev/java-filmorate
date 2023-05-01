package ru.yandex.practicum.filmorate.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.*;

@Data
@EqualsAndHashCode(of = {"id"})
@Slf4j
public class User {

    private Long id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\S*$")
    private String login;
    private String name;
    @Past
    private LocalDate birthday;
    private Set<Long> friendsId = new HashSet<>();

    public User(Long id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public User updateUser(User user) {
        this.setEmail(user.getEmail());
        this.setLogin(user.getLogin());
        this.setName(user.getName());
        this.setBirthday(user.getBirthday());

        return this;
    }

    public void addFriend(Long id) {
        friendsId.add(id);
    }

    public void addFriends(List<Long> friendsId) {
        for (Long id : friendsId) {
            this.friendsId.add(id);
        }
    }

    public void removeFriend(Long id) {
        friendsId.remove(id);
    }

    public boolean isFriend(Long id) {
        if (friendsId == null) {
            return false;
        } else {
            return friendsId.contains(id);
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> values = new HashMap<>();
        values.put("email", email);
        values.put("login", login);
        values.put("username", name);
        values.put("birthday", birthday);

        return values;
    }
}
