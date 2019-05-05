package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class MockStorage implements Storage {
    private Set<User> users = new HashSet<>();
    private HashMap<User, List<Stats>> statMap = new HashMap<>();

    @Override
    public List<User> getUsers() throws IOException {
        return new ArrayList<>(users);
    }

    @Override
    public List<Stats> getStatsForUser(User user) throws IOException {
        return statMap.get(user);
    }

    @Override
    public void updateStatsForUser(User user, List<Stats> stats) throws IOException {
        statMap.remove(user);
        statMap.put(user, stats);
    }

    @Override
    public Optional<User> getUserFromAuthentication(String username, Predicate<byte[]> passwordCheck) throws IOException {
        return users.stream().filter(x -> passwordCheck.test(x.getHash().getBytes())).findFirst();
    }

    @Override
    public void addUser(User user) throws IOException, UserExistsException {
        if (users.contains(user)){
            throw new UserExistsException();
        }
        users.add(user);
    }

    @Override
    public void updateUser(User user) throws IOException, UserDoesNotExistException {
        if (!users.contains(user)){
            throw new UserDoesNotExistException();
        }
    }
}
