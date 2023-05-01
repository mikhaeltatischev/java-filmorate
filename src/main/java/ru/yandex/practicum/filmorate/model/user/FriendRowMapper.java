package ru.yandex.practicum.filmorate.model.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendRowMapper implements RowMapper<Friends> {
    @Override
    public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long userId = rs.getLong("user_id");
        Long friendId = rs.getLong("friend_id");
        Boolean status = rs.getBoolean("status");

        return new Friends(userId, friendId, status);
    }
}
