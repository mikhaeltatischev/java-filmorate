package ru.yandex.practicum.filmorate.model.film;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class FilmGenres {
    private Long filmId;
    private Integer id;

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();

        value.put("film_id", getFilmId());
        value.put("id", getId());

        return value;
    }
}
