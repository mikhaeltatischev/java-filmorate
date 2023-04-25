INSERT INTO rating (rating_id, rating_name)
VALUES (1, 'G'),
       (2, 'PG'),
       (3, 'PG13'),
       (4, 'R');

INSERT INTO genre (genre_id, genre_name)
VALUES (1, 'Action'),
       (2, 'Comedy'),
       (3, 'Drama'),
       (4, 'Detective');

INSERT INTO users (user_id, email, login, username, birthday)
VALUES (1, 'mail@mail.ru', 'Boogiman', 'Sanya', '2000-01-01'),
       (2, 'yandex@yandex.ru', 'Stol', 'Vasya', '1999-03-03'),
       (3, 'gmail@gmail.com', 'Fooo', 'Dmitriy', '1987-04-04'),
       (4, 'yamail@mail.ru', 'Shadow', 'Ivan', '1995-10-10');

INSERT INTO films (film_id, film_name, description, release_date, duration, rating_id)
VALUES (1, 'Boys', 'The film about boys', '2020-02-02', 1000, 1),
       (2, 'Girls', 'The film about girls', '2010-01-01', 2000, 2),
       (3, 'People', 'The film about people', '2023-04-04', 3000, 3),
       (4, 'Dogs', 'The film about dogs', '2000-10-10', 4000, 4);

INSERT INTO likes (user_id, film_id)
VALUES (1, 1),
       (1, 4),
       (2, 1),
       (3, 2),
       (4, 3);

INSERT INTO friends (user_id, friend_id, status)
VALUES (1, 2, 'false'),
       (1, 3, 'true'),
       (1, 4, 'true'),
       (2, 4, 'true');

INSERT INTO film_genres (film_id, genre_id)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (3, 4),
       (4, 1);