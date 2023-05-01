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
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.model.genre.GenreRowMapper;

import java.util.List;

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
        this.getMpaName(film);
        this.saveFilmGenres(film);
        this.getGenreNames(film);
        log.info("Create film: " + film);
        return film;
    }

    @Override
    public Film update(Film film) {
        this.findFilm(film.getId());
        this.clearFilmGenres(film);
        this.saveFilmGenres(film);
        this.getGenreNames(film);
        this.getMpaName(film);

        String sql = "update films set film_name = ?, description = ?, release_date = ?, duration = ?, rate = ?, " +
                "mpa = ? where film_id = ?";

        jdbcTemplate.update(sql, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(),
                film.getRate(), film.getMpa().getId(), film.getId());

        log.info("Update film: " + film);
        return film;
    }

    @Override
    public List<Film> getFilms() {
        String sql = "select f.*, m.name from films as f inner join mpa as m on f.mpa = m.id";

        List<Film> films = jdbcTemplate.query(sql, new FilmRowMapper());

        for (Film film : films) {
            this.getGenreNames(film);
            this.getLikes(film);
        }

        log.info("Received films: " + films);
        return films;
    }

    @Override
    public Film findFilm(Long id) {
        String sql = "select f.*, m.name from films as f inner join mpa as m on f.mpa = m.id " +
                "where f.film_id = ? ";

        Film film = jdbcTemplate.query(sql, new FilmRowMapper(), id).stream().findAny()
                .orElseThrow(() -> new FilmNotFoundException("Film with id: " + id + " not found"));

        this.getLikes(film);
        this.getGenreNames(film);
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

    private void getLikes(Film film) {
        String sqlLikes = "select * from likes where film_id = ?";

        film.setLikes(jdbcTemplate.query(sqlLikes, new LikeRowMapper(), film.getId()));
    }

    private void getGenreNames(Film film) {
        String sql = "select * from genre where id in (select id from film_genres where film_id = ?)";

        List<Genre> genres = jdbcTemplate.query(sql, new GenreRowMapper(), film.getId());

        film.setGenres(genres);
    }

    private void getMpaName(Film film) {
        String sql = "select name from mpa where id = (select mpa from films where film_id = ?)";

        String mpaName = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"), film.getId())
                .toString();

        film.getMpa().setName(mpaName);
    }

    private void clearFilmGenres(Film film) {
        String sql = "delete from film_genres where film_id = ?";

        jdbcTemplate.update(sql, film.getId());
    }

    private void saveFilmGenres(Film film) {
        String sql = "insert into film_genres (film_id, id) values (?, ?)";

        for (Genre genre : film.getGenres()) {
            jdbcTemplate.update(sql, film.getId(), genre.getId());
        }
    }
}
