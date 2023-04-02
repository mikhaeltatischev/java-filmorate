package ru.yandex.practicum.filmorate.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.controller.FilmController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmController controller;

    @Test
    public void getFilms() throws Exception {
        mockMvc.perform(get("/films"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createAndUpdateFilm() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 1}";

        String updateFilmJson = "{\"name\": \"update name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 1}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(put("/films")
                        .content(updateFilmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createFilmWithBadName() throws Exception {
        String filmJson = "{\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createFilmWithBadDescription() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"" + "a".repeat(201) + "\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createFilmWithBadReleaseDate() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1000-03-25\",\"duration\": 100}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createFilmWithBadDuration() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": -100}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAndUpdateFilmWithBadName() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 1000}";

        String updateFilmJson = "{\"name\": \"\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 1000}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(put("/films")
                        .content(updateFilmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAndUpdateFilmWithBadDescription() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 2000}";

        String updateFilmJson = "{\"name\": \"name\",\"description\": \"" + "a".repeat(201) + "\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 2000}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(put("/films")
                        .content(updateFilmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAndUpdateFilmWithBadReleaseDate() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 3000}";

        String updateFilmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1000-03-25\",\"duration\": 100, \"id\": 3000}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(put("/films")
                        .content(updateFilmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAndUpdateFilmWithBadDuration() throws Exception {
        String filmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 4000}";

        String updateFilmJson = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": -100, \"id\": 4000}";

        mockMvc.perform(post("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(put("/films")
                        .content(updateFilmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateNotExistFilm() throws Exception {
        String filmJson = "{\"name\": \"not exist\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 10000}";

        mockMvc.perform(put("/films")
                        .content(filmJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFilmById() throws Exception {
        String filmOne = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"1967-03-25\",\"duration\": 100, \"id\": 5000}";

        String filmTwo = "{\"name\": \"name\",\"description\": \"description\", \"releaseDate\":" +
                " \"2000-03-25\",\"duration\": 100, \"id\": 6000}";

        mockMvc.perform(post("/films")
                .content(filmOne)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(post("/films")
                        .content(filmTwo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/films/5000"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
