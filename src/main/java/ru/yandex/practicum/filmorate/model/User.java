package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
}
