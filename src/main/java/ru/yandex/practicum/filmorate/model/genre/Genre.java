package ru.yandex.practicum.filmorate.model.genre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Genre {
    private Integer id;
    private String name;

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();

        value.put("id", getId());
        value.put("name", getName());

        return value;
    }
}
