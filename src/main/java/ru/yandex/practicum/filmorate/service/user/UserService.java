package ru.yandex.practicum.filmorate.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.userException.FriendAlreadyExistException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User addFriend(Long id, Long friendId) {
        User user = userStorage.findUser(id);
        User friend = userStorage.findUser(friendId);

        if (user.isFriend(friendId)) {
            log.info("The user {} has already been added as a friend", friend);
            throw new FriendAlreadyExistException("You are already friends");
        } else {
            log.info("User {} add to friends", friend);
            user.addFriend(friendId);
            friend.addFriend(id);
            userStorage.addFriend(id, friendId);

            return user;
        }
    }

    public User deleteFriend(Long id, Long friendId) {
        User user = userStorage.findUser(id);

        if (!user.isFriend(friendId)) {
            log.info("The user with id: {} already deleted", friendId);
            throw new FriendAlreadyExistException("User is not friends");
        } else {
            log.info("The user with id: {} removed from friends", friendId);
            user.removeFriend(friendId);
            userStorage.deleteFriend(id, friendId);

            return user;
        }
    }

    public List<User> getFriends(Long id) {
        List<User> listFriends = new ArrayList<>();
        User user = userStorage.findUser(id);

        for (Long friend : user.getFriendsId()) {
            listFriends.add(userStorage.findUser(friend));
        }

        return listFriends;
    }

    public List<User> getMutualFriends(Long id, Long otherId) {
        List<User> mutualFriends = new ArrayList<>();
        User user = userStorage.findUser(id);
        User otherUser = userStorage.findUser(otherId);

        for (Long friend : otherUser.getFriendsId()) {
            if (user.getFriendsId().contains(friend)) {
                mutualFriends.add(userStorage.findUser(friend));
            }
        }

        return mutualFriends;
    }

    public User create(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }
}
