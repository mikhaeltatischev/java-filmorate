package ru.yandex.practicum.filmorate.modelTest;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmTest {

    @Test
    public void createFilm() {
        Film film = new Film();
        film.setId(1);
        film.setName("name");
        film.setDescription("description");
        film.setDuration(100L);
        film.setReleaseDate(LocalDate.of(2000, 02, 02));

        assertTrue(film.isValidFilm(film));
    }

    @Test
    public void createFilmWithBadName() {
        Film film = new Film();
        film.setId(1);
        film.setName("");
        film.setDescription("description");
        film.setDuration(100L);
        film.setReleaseDate(LocalDate.of(2000, 02, 02));

        assertFalse(film.isValidFilm(film));
    }

    @Test
    public void createFilmWhenLengthDescriptionIs201() {
        Film film = new Film();
        film.setId(1);
        film.setName("name");
        film.setDescription("a".repeat(201));
        film.setDuration(100L);
        film.setReleaseDate(LocalDate.of(2000, 02, 02));

        assertFalse(film.isValidFilm(film));
    }

    @Test
    public void createFilmWithBadDuration() {
        Film film = new Film();
        film.setId(1);
        film.setName("name");
        film.setDescription("description");
        film.setDuration(-100L);
        film.setReleaseDate(LocalDate.of(2000, 02, 02));

        assertFalse(film.isValidFilm(film));
    }

    @Test
    public void createFilmWithBadReleaseDate() {
        Film film = new Film();
        film.setId(1);
        film.setName("name");
        film.setDescription("description");
        film.setDuration(100L);
        film.setReleaseDate(LocalDate.of(10, 02, 02));

        assertFalse(film.isValidFilm(film));
    }
}
