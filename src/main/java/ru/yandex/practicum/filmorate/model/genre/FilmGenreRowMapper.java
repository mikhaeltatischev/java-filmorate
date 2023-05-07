package ru.yandex.practicum.filmorate.model.genre;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.film.FilmGenres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmGenreRowMapper implements RowMapper<FilmGenres> {
    @Override
    public FilmGenres mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long filmId = rs.getLong("film_id");
        Integer id = rs.getInt("id");

        return new FilmGenres(filmId, id);
    }
}
