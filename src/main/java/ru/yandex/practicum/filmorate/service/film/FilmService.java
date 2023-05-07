package ru.yandex.practicum.filmorate.service.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.filmException.FilmAlreadyIsLikedException;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotLikedException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Likes;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;

@Slf4j
@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;
    private final GenreStorage genreStorage;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage, GenreStorage genreStorage, JdbcTemplate jdbcTemplate) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.genreStorage = genreStorage;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Film findFilm(Long id) {
        Film film = filmStorage.findFilm(id);

        load(film);

        log.info("Create film: " + film);

        return film;
    }

    public List<Film> getFilms() {
        List<Film> films = filmStorage.getFilms();

        loadButch(films);

        return films;
    }

    public Film update(Film film) {
        findFilm(film.getId());
        genreStorage.clearGenresForFilm(film.getId());
        genreStorage.setGenresForFilm(film);
        filmStorage.update(film);

        return film;
    }

    public Film create(Film film) {
        filmStorage.create(film);
        genreStorage.setGenresForFilm(film);
        return film;
    }

    public void like(Long id, Long userId) {
        Film film = filmStorage.findFilm(id);
        userStorage.findUser(userId);

        if (film.isLiked(userId)) {
            throw new FilmAlreadyIsLikedException("User with id: " + userId + " already liked the film");
        } else {
            filmStorage.like(id, userId);
        }
    }

    public void deleteLike(Long id, Long userId) {
        Film film = findFilm(id);
        userStorage.findUser(userId);

        if (film.isLiked(userId)) {
            filmStorage.deleteLike(id, userId);
        } else {
            throw new FilmNotLikedException("User with id: " + userId + " did not like");
        }
    }

    public List<Film> getPopularFilms(int count) {
        return getFilms().stream()
                .sorted(this::compare)
                .limit(count)
                .collect(Collectors.toList());
    }

    private int compare(Film f0, Film f1) {
        int result = f1.getLikes().size() - f0.getLikes().size();

        return result;
    }

    private void loadButch(List<Film> films) {
        String inSql = String.join(",", Collections.nCopies(films.size(), "?"));
        final Map<Long, Film> filmById = films.stream().collect(Collectors.toMap(Film::getId, identity()));
        final String sqlQuery = "select * from GENRE g, FILM_GENRES fg left join LIKES l on fg.FILM_ID = l.FILM_ID " +
                "where fg.ID = g.ID and fg.FILM_ID in (" + inSql + ")";

        jdbcTemplate.query(sqlQuery, (rs) -> {
            final Film film = filmById.get(rs.getLong("FILM_ID"));
            film.addGenre(Genre.makeGenre(rs, 0));
            film.addLike(Likes.makeLike(rs, 0));
        }, films.stream().map(Film::getId).toArray());
    }

    private void load(Film film) {
        String sql = "select * from GENRE g, FILM_GENRES fg left join LIKES l on fg.FILM_ID = l.FILM_ID " +
                "where fg.ID = g.ID and fg.FILM_ID = ?";

        jdbcTemplate.query(sql, rs -> {
            film.addGenre(Genre.makeGenre(rs, 0));
            film.addLike(Likes.makeLike(rs, 0));
        }, film.getId());
    }
}
