package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.controllerException.FilmAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.controllerException.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.filmException.ReleaseDateNotValidateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashSet;
import java.util.Set;

@RestController
@Slf4j
public class FilmController {

    private Set<Film> films = new HashSet<>();

    @GetMapping("/films")
    public Set<Film> getFilms() {
        log.info("Текущее количество фильмов: {}", films.size());

        return films;
    }

    @PutMapping(value = "/films")
    public Film update(@Valid @RequestBody Film film) throws FilmNotFoundException {
        for (Film currentFilm : films) {
            if (currentFilm.equals(film)) {
                currentFilm.updateFilm(film);
                log.info("Информация о обновленном фильме: {}", currentFilm);

                return currentFilm;
            }
        }
        throw new FilmNotFoundException();
    }

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) throws ReleaseDateNotValidateException {
        try {
            if (films.equals(film)) {
                throw new FilmAlreadyExistException();
            } else {
                if (film.isValidReleaseDate(film.getReleaseDate())) {
                    films.add(film);
                    log.info("Информация о добавленном фильме: {}", film);
                } else {
                    throw new ReleaseDateNotValidateException();
                }
            }
        } catch (FilmAlreadyExistException e) {
            log.info(e.getMessage());
        }

        return film;
    }
}
