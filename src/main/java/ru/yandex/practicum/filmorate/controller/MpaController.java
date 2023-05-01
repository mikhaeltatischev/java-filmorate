package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.mpa.Mpa;
import ru.yandex.practicum.filmorate.service.film.MpaService;

import java.util.List;

@RestController
@Slf4j
public class MpaController {

    private final MpaService mpaService;

    @Autowired
    public MpaController(MpaService mpaService) {
        this.mpaService = mpaService;
    }

    @GetMapping(value = "/mpa")
    public List<Mpa> getMpa() {
        return mpaService.getMpa();
    }

    @GetMapping(value = "/mpa/{id}")
    public Mpa findMpa(@PathVariable("id") Integer id) {
        return mpaService.findMpa(id);
    }
}