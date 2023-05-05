package ru.yandex.practicum.filmorate.service.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.filmException.FilmAlreadyIsLikedException;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotLikedException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Likes;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;
    private final GenreStorage genreStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage, GenreStorage genreStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.genreStorage = genreStorage;
    }

    public Film findFilm(Long id) {
        Set<Genre> genres = genreStorage.getGenresForFilm(id);
        Set<Likes> likes = filmStorage.getLikes(id);
        Film film = filmStorage.findFilm(id);

        film.setGenres(genres);
        film.setLikes(likes);
        log.info("Create film: " + film);

        return film;
    }

    public List<Film> getFilms() {
        List<Film> films = filmStorage.getFilms();

        for (Film film : films) {
            Set<Genre> genres = genreStorage.getGenresForFilm(film.getId());
            Set<Likes> likes = filmStorage.getLikes(film.getId());
            film.setGenres(genres);
            film.setLikes(likes);
        }

        return films;
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
}
