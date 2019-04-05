package ch.bbbaden.casinopalace.common;

import java.util.List;
import java.util.Optional;

public class Casino {
    private Storage storage;
    private User currentUser = null;

    public Casino(Storage storage) {
        this.storage = storage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUsers() {
        return storage.getUsers();
    }

    public Stats getStatsForUser(User user) {
        return storage.getStatsForUser(user);
    }

    public Optional<User> getUserFromAuthentication(String username, String password) {
        return storage.getUserFromAuthentication(username, password);
    }

    public User createUser(String username, String password){
        User user = new UserFactory().createUser(username, password);
        storage.addUser(user);
        return user;
    }
}
