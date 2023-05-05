package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.service.film.GenreService;

import java.util.stream.Stream;

@RestController
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/genres")
    public Stream<Genre> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping(value = "/genres/{id}")
    public Genre findGenre(@PathVariable("id") Integer id) {
        return genreService.findGenre(id);
    }
}
