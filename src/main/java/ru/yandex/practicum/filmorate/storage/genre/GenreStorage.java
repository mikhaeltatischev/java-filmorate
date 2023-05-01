package ru.yandex.practicum.filmorate.storage.genre;

import ru.yandex.practicum.filmorate.model.genre.Genre;

import java.util.List;

public interface GenreStorage {

    Genre findGenre(Integer id);

    List<Genre> getGenres();
}
