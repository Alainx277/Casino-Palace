package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.exception.UserExistsException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Casino {
    private final UserFactory userFactory;
    private final Storage storage;
    private User currentUser = null;

    public Casino(UserFactory userFactory, Storage storage) {
        this.userFactory = userFactory;
        this.storage = storage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUsers() throws IOException {
        return storage.getUsers();
    }

    public Stats getStatsForUser(User user) {
        return storage.getStatsForUser(user);
    }

    public Optional<User> getUserFromAuthentication(String username, String password) throws IOException {
        return storage.getUserFromAuthentication(username, hash -> userFactory.checkPassword(password, new String(hash)));
    }

    public User createUser(String username, String password) throws IOException, UserExistsException {
        User user = userFactory.createUser(username, password);
        storage.addUser(user);
        return user;
    }
}
