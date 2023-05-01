package ru.yandex.practicum.filmorate.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Friends {
    private Long userId;
    private Long friendId;
    private Boolean status;
}
