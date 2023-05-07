package ru.yandex.practicum.filmorate.model.mpa;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MpaRowMapper implements RowMapper<Mpa> {

    @Override
    public Mpa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer mpaId = rs.getInt("id");
        String mpaName = rs.getString("name");

        return new Mpa(mpaId, mpaName);
    }
}
