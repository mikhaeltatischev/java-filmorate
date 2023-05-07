package ru.yandex.practicum.filmorate.storage.genre;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.filmException.GenreNotFoundException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.model.genre.GenreRowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
@Slf4j
public class GenreDbStorage implements GenreStorage {

    private final JdbcTemplate jdbcTemplate;

    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Genre findGenre(Integer id) {
        String sql = "select * from genre where id = ?";

        Genre genre = jdbcTemplate.query(sql, new GenreRowMapper(), id).stream().findAny()
                                .orElseThrow(() -> new GenreNotFoundException("Genre with id: " + id + " not found"));
        log.info("Genre with id " + id + ": " + genre);
        return genre;
    }

    @Override
    public Set<Genre> getGenres() {
        String sql = "select * from genre";

        Set<Genre> genres = jdbcTemplate.query(sql, new GenreRowMapper()).stream().collect(Collectors.toSet());
        log.info("Genres: " + genres);
        return genres;
    }

    @Override
    public Set<Genre> getGenresForFilm(Long id) {
        String sql = "with cte as (select id from film_genres where film_id = ?) " +
                    "select * from genre join cte on genre.id = cte.id ";

        return jdbcTemplate.query(sql, new GenreRowMapper(), id).stream().collect(Collectors.toSet());
    }

    @Override
    public void setGenresForFilm(Film film) {
        String sql = "insert into film_genres (film_id, id) " +
                    "values (?, ?)";
        List<Genre> genres = new ArrayList<>(film.getGenres());

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, film.getId());
                ps.setLong(2, genres.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return genres.size();
            }
        });
    }

    @Override
    public void clearGenresForFilm(Long id) {
        String sql = "delete from film_genres where film_id = ?";

        jdbcTemplate.update(sql, id);
    }
}
