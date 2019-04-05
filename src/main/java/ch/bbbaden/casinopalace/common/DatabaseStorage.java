package ch.bbbaden.casinopalace.common;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class DatabaseStorage implements Storage {

    @Override
    public List<User> getUsers() {
        throw new NotImplementedException();
    }

    @Override
    public Stats getStatsForUser(User user) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<User> getUserFromAuthentication(String username, String password) {
        throw new NotImplementedException();
    }

    @Override
    public void addUser(User user) {
        throw new NotImplementedException();
    }

    @Override
    public void updateUser(User user) {
        throw new NotImplementedException();
    }
}
