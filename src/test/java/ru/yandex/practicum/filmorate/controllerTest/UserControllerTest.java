package ru.yandex.practicum.filmorate.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.controller.UserController;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController controller;

    @Test
    public void getUsers() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

    @Test
    public void createAndUpdateUser() throws Exception {
        String userJson = "{\"login\": \"login\",\"name\": \"name\",\"id\": 1,\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"1976-09-20\"}";

        String updateUserJson = "{\"login\": \"updateLogin\",\"name\": \"name\",\"id\": 1,\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"1976-09-20\"}";

        this.mockMvc.perform(post("/users")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(put("/users")
                        .content(updateUserJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createUserWithBadLogin() throws Exception {
        String json = "{\"login\": \"bad login\",\"name\": \"name\",\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"1976-09-20\"}";

        this.mockMvc.perform(post("/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserWithBadEmail() throws Exception {
        String json = "{\"login\": \"login\",\"name\": \"name\",\"email\":" +
                " \"bad email\",\"birthday\": \"1976-09-20\"}";

        this.mockMvc.perform(post("/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserWithBadBirthday() throws Exception {
        String json = "{\"login\": \"login\",\"name\": \"name\",\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"3000-09-20\"}";

        this.mockMvc.perform(post("/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserWithBadLogin() throws Exception {
        String userJson = "{\"login\": \"login\",\"name\": \"name\",\"id\": 1000,\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"1976-09-20\"}";

        String updateUserJson = "{\"login\": \"update Login\",\"name\": \"name\",\"id\": 1000,\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"1976-09-20\"}";


        this.mockMvc.perform(post("/users")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(put("/users")
                        .content(updateUserJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserWithBadEmail() throws Exception {
        String userJson = "{\"login\": \"login\",\"name\": \"name\",\"id\": 2000,\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"1976-09-20\"}";

        String updateUserJson = "{\"login\": \"updateLogin\",\"name\": \"name\",\"id\": 2000,\"email\":" +
                " \"bad email\",\"birthday\": \"1976-09-20\"}";


        this.mockMvc.perform(post("/users")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(put("/users")
                        .content(updateUserJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserWithBadBirthday() throws Exception {
        String userJson = "{\"login\": \"login\",\"name\": \"name\",\"id\": 3000,\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"1976-09-20\"}";

        String updateUserJson = "{\"login\": \"updateLogin\",\"name\": \"name\",\"id\": 3000,\"email\":" +
                " \"mail@yandex.ru\",\"birthday\": \"3000-09-20\"}";


        this.mockMvc.perform(post("/users")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(put("/users")
                        .content(updateUserJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
