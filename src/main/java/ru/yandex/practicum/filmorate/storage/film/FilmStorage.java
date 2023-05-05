package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Likes;

import java.util.List;
import java.util.Set;

public interface FilmStorage {

    Film create(Film film);

    Film update(Film film);

    List<Film> getFilms();

    Film findFilm(Long id);

    void like(Long id, Long userId);

    void deleteLike(Long id, Long userId);

    Set<Likes> getLikes(Long filmId);
}
