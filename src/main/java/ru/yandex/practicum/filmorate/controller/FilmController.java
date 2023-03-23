package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.controllerException.FilmAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.controllerException.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class FilmController {

    private static int id = 1;
    private Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public List<Film> getFilms() {
        log.info("Текущее количество фильмов: {}", films.size());

        return getFilms(films);
    }

    @PutMapping(value = "/films")
    public Film update(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            film.updateFilm(film);
            films.put(film.getId(), film);
            log.info("Информация о обновленном фильме: {}", film);

            return film;
        }

        throw new FilmNotFoundException();
    }

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            throw new FilmAlreadyExistException();
        } else {
            film.setId(id);
            id++;
            films.put(film.getId(), film);
            log.info("Информация о добавленном фильме: {}", film);
        }

        return film;
    }

    private List<Film> getFilms(Map<Integer, Film> filmsMap) {
        List<Film> films = new ArrayList<>();

        for (Film film : filmsMap.values()) {
            films.add(film);
        }

        return films;
    }
}
