package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.annotation.LaterThanFirstFilm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Slf4j
public class Film {

    private static final int LENGTH_DESCRIPTION = 200;

    private Long id;
    @NotBlank
    private String name;
    @Size(max = LENGTH_DESCRIPTION)
    private String description;
    @LaterThanFirstFilm
    private LocalDate releaseDate;
    @Positive
    private Long duration;
    private final Set<Long> likes = new HashSet<>();

    public Film updateFilm(Film film) {
        this.setName(film.getName());
        this.setDuration(film.getDuration());
        this.setDescription(film.getDescription());
        this.setReleaseDate(film.getReleaseDate());

        return film;
    }

    public void addLike(Long id) {
        likes.add(id);
    }

    public void removeLike(Long id) {
        likes.remove(id);
    }

    public boolean isLiked(Long id) {
        return likes != null && likes.contains(id);
    }
}
