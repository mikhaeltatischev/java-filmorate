package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exception.filmException.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.userException.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.mpa.Mpa;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmorateApplicationTests {

	private final UserDbStorage userStorage;
	private final FilmDbStorage filmStorage;

	@Test
	public void testFindUnknownUser() {
		try {
			Optional<User> optionalUser = Optional.of(userStorage.findUser(1L));
		} catch (UserNotFoundException e) {
			assertEquals("User with id: 1 not found", e.getMessage());
		}
	}

	@Test
	public void testFindUnknownFilm() {
		try {
			Optional<Film> optionalFilm = Optional.of(filmStorage.findFilm(1L));
		} catch (FilmNotFoundException e) {
			assertEquals("Film with id: 1 not found", e.getMessage());
		}
	}

	@Test
	public void testFindFilmById() {
		Film filmOne = new Film(1L, "name", "description", LocalDate.of(2000, 01,
					01), 1000, new Mpa(1));
		filmStorage.create(filmOne);

		Optional<Film> optionalFilm = Optional.of(filmStorage.findFilm(1L));

		assertThat(optionalFilm)
				.isPresent()
				.hasValueSatisfying(film ->
						assertThat(film).hasFieldOrPropertyWithValue("id", 1L)
				);
	}

	@Test
	public void testFindUserById() {
		User userOne = new User(1L, "mail@mail.ru", "login", "name", LocalDate.of(2000, 01,
						01));

		userStorage.create(userOne);

		Optional<User> optionalUser = Optional.of(userStorage.findUser(1L));

		assertThat(optionalUser)
				.isPresent()
				.hasValueSatisfying(user ->
						assertThat(user).hasFieldOrPropertyWithValue("id", 1L)
				);
	}

	@Test
	public void testGetUsers() {
		List<User> users = userStorage.getUsers();

		assertEquals(0, users.size());
	}

	@Test
	public void testGetFilms() {
		List<Film> films = filmStorage.getFilms();

		assertEquals(0, films.size());
	}
}