package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        return filmService.getFilmStorage().getFilms();
    }

    @PutMapping(value = "/films")
    public Film update(@Valid @RequestBody Film film) {
        return filmService.getFilmStorage().update(film);
    }

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        return filmService.getFilmStorage().create(film);
    }

    @PutMapping(value = "/films/{id}/like/{userId}")
    public Film like(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        return filmService.like(id, userId);
    }

    @DeleteMapping(value = "/films/{id}/like/{userId}")
    public Film deleteLike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        return filmService.deleteLike(id, userId);
    }

    @GetMapping(value = "/films/popular")
    public List<Film> getPopular(@RequestParam(value = "count", defaultValue = "10", required = false) Integer count) {
        return filmService.getPopularFilms(count);
    }

    @GetMapping(value = "/films/{id}")
    public Film getFilmById(@PathVariable(value = "id") Long id) {
        return filmService.getFilmStorage().findFilm(id);
    }
}
