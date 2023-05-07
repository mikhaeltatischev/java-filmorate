package ru.yandex.practicum.filmorate.model.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("user_id");
        String email = rs.getString("email");
        String login = rs.getString("login");
        String name = rs.getString("username");
        LocalDate birthday = rs.getDate("birthday").toLocalDate();

        User user = new User(id, email, login, name, birthday);

        return user;
    }
}
