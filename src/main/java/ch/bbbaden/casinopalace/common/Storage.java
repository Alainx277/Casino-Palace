package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * Responsible for storing all data
 */
public interface Storage {

    /**
     * @return All users stored
     */
    List<User> getUsers() throws IOException;

    /**
     * @param user The user to get the stats for
     * @return The users stats or null if the stats for the user don't exist
     */
    List<Stats> getStatsForUser(User user) throws IOException;

    /**
     * @param user The user to update the stats for
     * @param stats The values to update
     */
    void updateStatsForUser(User user, List<Stats> stats) throws IOException;

    /**
     * @param username The user's username
     * @param passwordCheck A predicate that checks the users password
     * @return The user or an empty optional if no matching user was found
     */
    Optional<User> getUserFromAuthentication(String username, Predicate<byte[]> passwordCheck) throws IOException;

    /**
     * @param user Adds a user to storage
     */
    void addUser(User user) throws IOException, UserExistsException;

    /**
     * @param user The user to update
     */
    void updateUser(User user) throws IOException, UserDoesNotExistException;
}
