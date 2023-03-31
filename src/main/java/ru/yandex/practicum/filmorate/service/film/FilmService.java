package ru.yandex.practicum.filmorate.service.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.filmException.FilmAlreadyIsLikedException;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotLikedException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmService {

    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film like(Long id, Long userId) {
        Film film = filmStorage.findFilm(id);

        if (film.isLiked(userId)) {
            log.info("User with id: {} already liked the film", userId);
            throw new FilmAlreadyIsLikedException("User with id: " + userId + " already liked the film");
        } else {
            log.info("User with id {} liked film", userId);
            film.addLike(userId);
        }

        return film;
    }

    public Film deleteLike(Long id, Long userId) {
        Film film = filmStorage.findFilm(id);

        if (!film.isLiked(userId)) {
            log.info("User with id: {} did not like", userId);
            throw new FilmNotLikedException("User with id: " + userId + " did not like");
        } else {
            log.info("User with id {} delete like", userId);
            film.removeLike(userId);
        }

        return film;
    }

    public List<Film> getPopularFilms(int count) {
        return filmStorage.getFilms().stream()
                .sorted((f0, f1) -> compare(f0, f1))
                .limit(count)
                .collect(Collectors.toList());
    }

    private int compare(Film f0, Film f1) {
        int result = f1.getLikes().size() - (f0.getLikes().size());

        return result;
    }

    public FilmStorage getFilmStorage() {
        return filmStorage;
    }
}
