package ru.yandex.practicum.filmorate.storage.genre;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.genre.Genre;

import java.util.Set;

public interface GenreStorage {

    Genre findGenre(Integer id);

    Set<Genre> getGenres();

    Set<Genre> getGenresForFilm(Long id);

    void setGenresForFilm(Film film);

    void clearGenresForFilm(Long id);
}
