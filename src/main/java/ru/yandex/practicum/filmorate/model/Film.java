package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.filmException.DurationNotValidateException;
import ru.yandex.practicum.filmorate.exception.filmException.NameNotValidateException;
import ru.yandex.practicum.filmorate.exception.filmException.ReleaseDateNotValidateException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = {"id"})
@Slf4j
public class Film {

    private static final LocalDate EARLIEST_DATE_RELEASE = LocalDate.of(1895, 12, 28);
    private static final int LENGTH_DESCRIPTION = 200;

    private static int inc = 1;
    private int id = inc++;
    @NotBlank
    private String name;
    @Size(max = LENGTH_DESCRIPTION)
    private String description;
    private LocalDate releaseDate;
    @Positive
    private Long duration;

    public Film updateFilm(Film film) {
        if (isValidName(film.getName())) {
            this.setName(film.getName());
        }

        if (isValidDuration(film.getDuration())) {
            this.setDuration(film.getDuration());
        }

        if (isValidDescription(film.getDescription())) {
            this.setDescription(film.getDescription());
        }

        if (isValidReleaseDate(film.getReleaseDate())) {
            this.setReleaseDate(film.getReleaseDate());
        }

        return film;
    }

    public boolean isValidFilm(Film film) {
        if (isValidName(film.getName()) && isValidDescription(film.getDescription()) &&
                isValidDuration(film.getDuration()) && isValidReleaseDate(film.getReleaseDate())) {
            return true;
        }

        return false;
    }

    private boolean isValidName(String name) {
        try {
            if (name == null || name.isBlank()) {
                throw new NameNotValidateException();
            }
        } catch (NameNotValidateException e) {
            log.info(e.getMessage());

            return false;
        }

        return true;
    }

    private boolean isValidDescription(String description) {
        try {
            if (description.length() > LENGTH_DESCRIPTION) {
                throw new NameNotValidateException();
            }
        } catch (NameNotValidateException e) {
            log.info(e.getMessage());

            return false;
        }

        return true;
    }

    public boolean isValidReleaseDate(LocalDate releaseDate) {
        try {
            if (releaseDate.isBefore(EARLIEST_DATE_RELEASE)) {
                throw new ReleaseDateNotValidateException();
            }
        } catch (ReleaseDateNotValidateException e) {
            log.info(e.getMessage());

            return false;
        }

        return true;
    }

    private boolean isValidDuration(Long duration) {
        try {
            if (duration < 0) {
                throw new DurationNotValidateException();
            }
        } catch (DurationNotValidateException e) {
            log.info(e.getMessage());

            return false;
        }

        return true;
    }
}
