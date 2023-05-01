package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.film.Film;

import java.util.List;

public interface FilmStorage {

    Film create(Film film);

    Film update(Film film);

    List<Film> getFilms();

    Film findFilm(Long id);

    void like(Long id, Long userId);

    void deleteLike(Long id, Long userId);
}
