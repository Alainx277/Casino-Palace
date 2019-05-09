package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseStorageTest {
    private final String testDB = "casinotest";
    private Connection connection;
    private DatabaseStorage storage;
    private User[] users;
    private ArrayList<Stats> playerStats;

    @Before
    public void setUp() throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
            Assume.assumeTrue("Database connection is unstable", connection.isValid(200));
        } catch (Exception e) {
            Assume.assumeNoException("Database isn't reachable", e);
        }
        storage = new DatabaseStorage("localhost", testDB, "root", "");
        users = new User[]{
                new User("IAmARobotAndThisIsDeep", "password1", new BigDecimal(100)),
                new User("NotARobotTrustMe", "1password", new BigDecimal(500))
        };
        for (User user : users) {
            storage.addUser(user);
        }

        playerStats = new ArrayList<>();
        Stats e = new Stats(Game.Poker);
        e.put("Gewonnen", BigDecimal.valueOf(50));
        playerStats.add(e);
        storage.updateStatsForUser(users[0], playerStats);
    }

    @After
    public void tearDown() throws Exception {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE IF EXISTS " + testDB);
    }

    @Test
    public void getUsers() throws IOException {
        List<User> storedUsers = storage.getUsers();
        for (User user : users) {
            assertTrue(storedUsers.contains(user));
        }
    }

    @Test
    public void getStatsForUser() throws IOException {
        List<Stats> newStats = storage.getStatsForUser(users[0]);
        for (Stats playerStat : playerStats) {
            assertTrue(newStats.contains(playerStat));
        }
    }

    @Test
    public void updateStatsForUser() throws IOException {
        Stats e = new Stats(Game.Blackjack);
        e.put("Verloren", BigDecimal.valueOf(21));
        playerStats.add(e);
        storage.updateStatsForUser(users[0], playerStats);

        List<Stats> newStats = storage.getStatsForUser(users[0]);
        assertTrue(newStats.contains(e));
    }

    @Test
    public void getUserFromAuthentication() throws IOException {
        User user = users[0];
        assertTrue(storage.getUserFromAuthentication(user.getUsername(), bytes -> {
            String s = new String(bytes).split("\0")[0];
            return s.equals(user.getHash());
        }).isPresent());
    }

    @Test
    public void getUserFromAuthenticationIncorrectUsername() throws IOException {
        assertFalse(storage.getUserFromAuthentication("IDoNotExist", bytes -> new String(bytes).split("\0")[0].equals("MeNeither")).isPresent());
    }

    @Test
    public void addUser() throws IOException, UserExistsException {
        User user = new User("BrandNewHuman", "very secure password", new BigDecimal(1));
        storage.addUser(user);
        assertTrue(storage.getUsers().contains(user));
    }

    @Test(expected = UserExistsException.class)
    public void addUserAlreadyExists() throws IOException, UserExistsException {
        storage.addUser(users[0]);
    }

    @Test
    public void updateUser() throws IOException, UserDoesNotExistException {
        BigDecimal newValue = new BigDecimal(50000);
        users[0].setChips(newValue);
        storage.updateUser(users[0]);
        for (User user : storage.getUsers()) {
            if (users[0].equals(user)){
                assertEquals(0, newValue.compareTo(user.getChips()));
                break;
            }
        }
    }
}