package ru.yandex.practicum.filmorate.model.mpa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mpa {
    private Integer id;
    private String name;

    public Mpa(Integer id) {
        this.id = id;
    }
}
