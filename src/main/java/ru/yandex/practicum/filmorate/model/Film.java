package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.annotation.LaterThanFirstFilm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = {"id"})
@Slf4j
public class Film {

    private static final int LENGTH_DESCRIPTION = 200;

    private int id;
    @NotBlank
    private String name;
    @Size(max = LENGTH_DESCRIPTION)
    private String description;
    @LaterThanFirstFilm
    private LocalDate releaseDate;
    @Positive
    private Long duration;

    public Film updateFilm(Film film) {
        this.setName(film.getName());
        this.setDuration(film.getDuration());
        this.setDescription(film.getDescription());
        this.setReleaseDate(film.getReleaseDate());

        return film;
    }
}
