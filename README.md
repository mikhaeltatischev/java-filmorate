# Фильморейт
Проект создан, чтобы помочь с выбором фильма. Рекомендации основываются на просмотрах друзей и их оценок.  

# Используемые технологии
Spring, H2, Lombok, JDBC  

### Diagram of filmorate database.
![Screenshot of the diagram of database.](/src/main/resources/images/diagram.png/)  
request examples:

### request of get films  
- **SELECT** film_id **AS** id,  
film_name **AS** name  
**FROM** films;

### request of get top-3 popular film  
- **SELECT** film_id **AS** id  
**FROM** likes  
**GROUP BY** id  
**ORDER BY COUNT**(user_id) **DESC**  
**LIMIT** 3;  

### request of get friends of user  
- **SELECT** friend_id **AS** friend  
**FROM** friends **AS** f  
**WHERE** user_id = 1  
**AND** status = 'true';

### request of get mutual friends of two user  
- **SELECT** friend_id **AS** friend  
**FROM** friends **AS** f  
**WHERE** user_id = 4  
**AND** status = 'true'  
**AND** friend_id **IN**  
(**SELECT** friend_id **AS** friend  
**FROM** friends **AS** f  
**WHERE** user_id = 1  
**AND** status = 'true');

### request of get users  
- **SELECT** user_id **AS** id,  
username **AS** name  
**FROM** users;

### request of get genres
- **SELECT** name  
**FROM** genre;

### request of get mpa
- **SELECT** name  
**FROM** mpa;
