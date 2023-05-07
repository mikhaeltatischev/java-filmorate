package ru.yandex.practicum.filmorate.model.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Genre implements Comparable<Genre> {
    private Integer id;
    private String name;

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();

        value.put("id", getId());
        value.put("name", getName());

        return value;
    }

    public static Genre makeGenre(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Genre(id, name);
    }

    @Override
    public int compareTo(Genre o) {
        return this.getId() - o.getId();
    }
}
