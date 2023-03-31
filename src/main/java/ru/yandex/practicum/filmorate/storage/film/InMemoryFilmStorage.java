package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.filmException.FilmAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private static Long id = 1L;
    private Map<Long, Film> films = new HashMap<>();

    @Override
    public Film create(Film film) {
        if (!films.containsValue(film)) {
            if (film.getId() == null) {
                film.setId(id);
                id++;
            }
            films.put(film.getId(), film);
            log.info("Information about the added film: {}", film);

            return film;
        }
        throw new FilmAlreadyExistException("Film with id: " + film.getId() + " already exist");
    }

    @Override
    public Film update(Film film) {
        if (findFilm(film) != null) {
            film.updateFilm(film);
            films.put(film.getId(), film);
            log.info("Information about the updated film: {}", film);

            return film;
        }
        throw new FilmNotFoundException("Film with id: " + film.getId() + " not found");
    }

    @Override
    public List<Film> getFilms() {
        List<Film> listOfFilms = new ArrayList<>();

        for (Film film : films.values()) {
            listOfFilms.add(film);
        }

        log.info("Current number of films: {}", listOfFilms.size());

        return listOfFilms;
    }

    @Override
    public Film findFilm(Film film) {
        if (films.get(film.getId()) == null) {
            throw new FilmNotFoundException("Film with id: " + id + " not found");
        }

        return films.get(film.getId());
    }

    @Override
    public Film findFilm(Long id) {
        if (films.get(id) == null) {
            throw new FilmNotFoundException("Film with id: " + id + " not found");
        }
        return films.get(id);
    }
}
