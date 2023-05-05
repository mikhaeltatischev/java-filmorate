package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.FilmRowMapper;
import ru.yandex.practicum.filmorate.model.film.LikeRowMapper;
import ru.yandex.practicum.filmorate.model.film.Likes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Primary
@Slf4j
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film create(Film film) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("films")
                .usingGeneratedKeyColumns("film_id");

        Long filmId = simpleJdbcInsert.executeAndReturnKey(film.toMap()).longValue();

        film.setId(filmId);

        return film;
    }

    @Override
    public Film update(Film film) {
        String sql = "update films set film_name = ?, description = ?, release_date = ?, duration = ?, rate = ?, " +
                "mpa = ? where film_id = ?";

        jdbcTemplate.update(sql, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(),
                film.getRate(), film.getMpa().getId(), film.getId());

        log.info("Update film: " + film);

        return film;
    }

    @Override
    public List<Film> getFilms() {
        String sql = "select f.*, m.name from films as f left join mpa as m on f.mpa = m.id";

        List<Film> films = jdbcTemplate.query(sql, new FilmRowMapper());

        log.info("Received films: " + films);

        return films;
    }

    @Override
    public Film findFilm(Long id) {
        String sql = "select f.*, m.name from films as f left join mpa as m on f.mpa = m.id where f.film_id = ?";

        Film film = jdbcTemplate.query(sql, new FilmRowMapper(), id).stream().findAny()
                .orElseThrow(() -> new FilmNotFoundException("Film with id: " + id + " not found"));

        log.info("Film with id:" + id + " " + film);

        return film;
    }

    @Override
    public void like(Long id, Long userId) {
        String sql = "insert into likes (user_id, film_id) " +
                "values (?, ?)";

        jdbcTemplate.update(sql, userId, id);
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        String sql = "delete from likes where user_id = ? and film_id = ?";

        jdbcTemplate.update(sql, userId, id);
    }

    public Set<Likes> getLikes(Long filmId) {
        String sql = "select * from likes where film_id = ?";

        Set<Likes> likes = new HashSet<>(jdbcTemplate.query(sql, new LikeRowMapper(),filmId));

        return likes;
    }
}
