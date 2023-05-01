package ru.yandex.practicum.filmorate.model.film;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.annotation.LaterThanFirstFilm;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.model.mpa.Mpa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

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
    private Integer duration;
    private Mpa mpa;
    private List<Genre> genres = new ArrayList<>();
    private List<Likes> likes = new ArrayList<>();
    private Integer rate;

    public Film(Long id, String name, String description, LocalDate releaseDate, Integer duration, Mpa mpa) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.mpa= mpa;
    }

    public void addLike(Likes like) {
        likes.add(like);
    }

    public void removeLike(Long id) {
        if (likes.size() == 0) {
            return;
        }

        for (int i = 0; i < likes.size(); i++) {
            if (likes.get(i).getUserId() == id) {
                likes.remove(i);
            }
        }
    }

    public boolean isLiked(Long id) {
        if (likes.size() == 0) {
            return false;
        }

        for (Likes like : likes) {
            if (like.getUserId() == id) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> values = new HashMap<>();
        values.put("film_id", getId());
        values.put("film_name", getName());
        values.put("description", getDescription());
        values.put("release_date", getReleaseDate());
        values.put("duration", getDuration());
        values.put("mpa", getMpa().getId());
        values.put("genres", getGenres());

        return values;
    }
}
