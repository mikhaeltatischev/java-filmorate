package ru.yandex.practicum.filmorate.model.film;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class Likes {
    private Long userId;
    private Long filmId;

    public static Likes makeLike(ResultSet rs, int rowNum) throws SQLException {
        Long userId = rs.getLong("user_id");
        Long filmId = rs.getLong("film_id");

        return new Likes(userId, filmId);
    }
}
