package ru.yandex.practicum.filmorate.model.film;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeRowMapper implements RowMapper<Likes> {

    @Override
    public Likes mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long userId = rs.getLong("user_id");
        Long filmId = rs.getLong("film_id");

        return new Likes(userId, filmId);
    }
}
