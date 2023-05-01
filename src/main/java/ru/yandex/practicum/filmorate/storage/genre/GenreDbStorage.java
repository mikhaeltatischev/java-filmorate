package ru.yandex.practicum.filmorate.storage.genre;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.filmException.GenreNotFoundException;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.model.genre.GenreRowMapper;

import java.util.List;

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
    public List<Genre> getGenres() {
        String sql = "select * from genre";

        List<Genre> genres = jdbcTemplate.query(sql, new GenreRowMapper());
        log.info("Genres: " + genres);
        return genres;
    }
}
