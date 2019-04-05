package ch.bbbaden.casinopalace.common;

import java.util.List;
import java.util.Optional;


/**
 * Responsible for storing all data
 */
public interface Storage {

    /**
     * @return All users stored
     */
    List<User> getUsers();

    /**
     * @param user The user to get the stats for
     * @return The users stats or null if the stats for the user don't exist
     */
    Stats getStatsForUser(User user);

    /**
     * @param username The user's username
     * @param password The user's password
     * @return The user or an empty optional if no matching user was found
     */
    Optional<User> getUserFromAuthentication(String username, String password);

    /**
     * @param user Adds a user to storage
     */
    void addUser(User user);

    /**
     * @param user The user to update
     */
    void updateUser(User user);
}
