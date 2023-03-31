package ru.yandex.practicum.filmorate.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.userException.FriendAlreadyExistException;
import ru.yandex.practicum.filmorate.model.User;
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

        if (user.isFriend(friend.getId())) {
            log.info("The user {} has already been added as a friend", friend);
            throw new FriendAlreadyExistException("You are already friends");
        } else {
            log.info("User {} add to friends", friend);
            user.addFriend(friend.getId());
            friend.addFriend(user.getId());
        }
        return user;
    }

    public User removeFriend(Long id, Long friendId) {
        User user = userStorage.findUser(id);
        User friend = userStorage.findUser(friendId);

        if (!user.isFriend(friend.getId())) {
            log.info("The user {} already deleted", friend);
            throw new FriendAlreadyExistException("User is not friends");
        } else {
            log.info("User {} removed from friends", friend);
            user.removeFriend(friend.getId());
            friend.removeFriend(user.getId());
        }
        return user;
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

    public UserStorage getUserStorage() {
        return userStorage;
    }
}
