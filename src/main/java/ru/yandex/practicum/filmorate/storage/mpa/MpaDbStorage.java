package ru.yandex.practicum.filmorate.storage.mpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.filmException.MpaNotFoundException;
import ru.yandex.practicum.filmorate.model.mpa.Mpa;
import ru.yandex.practicum.filmorate.model.mpa.MpaRowMapper;

import java.util.List;

@Slf4j
@Primary
@Component
public class MpaDbStorage implements MpaStorage {

    private final JdbcTemplate jdbcTemplate;

    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Mpa findMpa(Integer id) {
        String sql = "select * from mpa where id = ?";

        Mpa mpa = jdbcTemplate.query(sql, new MpaRowMapper(), id).stream().findAny()
                                .orElseThrow(() -> new MpaNotFoundException("Mpa with id: " + id + " not found"));
        log.info("Mpa with id " + id + ": " + mpa);
        return mpa;
    }

    @Override
    public List<Mpa> getMpa() {
        String sql = "select * from mpa";

        List<Mpa> mpa = jdbcTemplate.query(sql, new MpaRowMapper());
        log.info("Mpa: " + mpa);
        return mpa;
    }
}
