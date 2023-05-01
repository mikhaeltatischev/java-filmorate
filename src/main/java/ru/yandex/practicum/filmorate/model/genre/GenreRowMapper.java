package ru.yandex.practicum.filmorate.model.genre;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        String genreName = rs.getString("name");
        Integer genreId = rs.getInt("id");

        return new Genre(genreId, genreName);
    }
}
