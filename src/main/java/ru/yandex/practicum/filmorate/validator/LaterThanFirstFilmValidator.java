package ru.yandex.practicum.filmorate.validator;

import ru.yandex.practicum.filmorate.annotation.LaterThanFirstFilm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class LaterThanFirstFilmValidator implements ConstraintValidator<LaterThanFirstFilm, LocalDate> {

    private final LocalDate date = LocalDate.of(1895, 12, 28);

    @Override
    public void initialize(LaterThanFirstFilm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isAfter(date);
    }
}
