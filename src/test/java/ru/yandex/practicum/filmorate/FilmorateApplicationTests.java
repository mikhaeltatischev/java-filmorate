package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmorateApplicationTests {

	private final UserDbStorage userStorage;
	private final FilmDbStorage filmStorage;

	@Test
	public void testFindUnknownUser() {
		Optional<User> optionalUser = Optional.of(userStorage.findUser(10000L));

		assertThat(userStorage.findUser(10000L))
				.hasAllNullFieldsOrPropertiesExcept("")
	}

	@Test
	public void testFindFilmById() {
		Optional<Film> optionalFilm = Optional.of(filmStorage.findFilm(10000L));

		assertThat(optionalFilm)
				.isPresent()
				.hasValueSatisfying(film ->
						assertThat(film).hasFieldOrPropertyWithValue("id", 10000L)
				);
	}
}