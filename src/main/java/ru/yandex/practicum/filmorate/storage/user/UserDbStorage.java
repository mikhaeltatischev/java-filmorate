package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.userException.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.model.user.UserRowMapper;

import java.util.List;

@Slf4j
@Component
@Primary
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        String sql = "select u.*, f.friend_id from users as u left join friends as f on u.user_id = f.user_id";

        List<User> users = jdbcTemplate.query(sql, new UserRowMapper());

        for (User user : users) {
            this.getFriends(user);
        }

        log.info("Received users: " + users);

        return users;
    }

    @Override
    public User create(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("user_id");

        Long userId = simpleJdbcInsert.executeAndReturnKey(user.toMap()).longValue();
        user.setId(userId);

        log.info("Create user: " + user);

        return user;
    }

    @Override
    public User update(User user) {
        String sql = "update users set " +
                "email = ?, login = ?, username = ?, birthday = ?" +
                "where user_id = ?";

        jdbcTemplate.update(sql, user.getEmail(), user.getLogin(), user.getName(), user.getBirthday(), user.getId());

        log.info("Update user: " + user);

        return user;
    }

    @Override
    public User findUser(Long id) {
        String sql = "select u.* from users as u left join friends as f on u.user_id = f.user_id where u.user_id = ?";

        User user = jdbcTemplate.query(sql, new UserRowMapper(), id).stream().findAny()
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        this.getFriends(user);

        log.info("User with id: " + id + " " + user);

        return user;
    }

    @Override
    public void addFriend(Long id, Long friendId) {
        String sql = "insert into friends (user_id, friend_id)" +
                "values (?, ?)";

        jdbcTemplate.update(sql, id, friendId);
    }

    @Override
    public void deleteFriend(Long id, Long friendId) {
        String sql = "delete from friends where user_id = ? and friend_id = ?";

        jdbcTemplate.update(sql, id, friendId);
    }

    private void getFriends(User user) {
        String sql = "select friend_id from friends where user_id = ?";

        List<Long> friends = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("friend_id"), user.getId());
        user.addFriends(friends);
    }
}
