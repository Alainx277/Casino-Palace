package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.function.Predicate;

public class DatabaseStorage implements Storage {
    private Connection connection;

    public DatabaseStorage(String endpoint, String dbname, String user, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + endpoint + "/" + dbname + "?createDatabaseIfNotExist=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, password);

        // Ensure tables exist
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS `" + dbname + "`.`Player` ( `Player_ID` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(50) NOT NULL , `hash` BINARY(60) NOT NULL , `money` DECIMAL(12,4) NOT NULL , `admin` BOOLEAN NOT NULL , PRIMARY KEY (`Player_ID`)) ENGINE = InnoDB;");
        statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS `" + dbname + "`.`Statistic` ( `Statistic_ID` INT NOT NULL AUTO_INCREMENT , `Player_ID` INT NOT NULL , `game` ENUM('poker','blackjack','yatzy','roulette') NOT NULL , `name` VARCHAR(50) NOT NULL , `value` DECIMAL(12,4) NOT NULL , PRIMARY KEY (`Statistic_ID`), FOREIGN KEY (`Player_ID`) REFERENCES Player(Player_ID)) ENGINE = InnoDB;");

    }

    @Override
    public List<User> getUsers() throws IOException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Player");
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUserFromResults(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public HashMap<Game, Stats> getStatsForUser(User user) throws IOException {
        try {
            HashMap<Game, Stats> map = new HashMap<>();

            PreparedStatement statement = connection.prepareStatement("SELECT Statistic.game, Statistic.name, Statistic.value FROM Statistic INNER JOIN Player ON Statistic.Player_ID = Player.Player_ID WHERE Player.name = ? ORDER BY Statistic.game");
            statement.setString(1, user.getUsername());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String enumString = resultSet.getString(1);
                Game game = Game.valueOf(enumString.substring(0, 1).toUpperCase() + enumString.substring(1));
                Stats stats = map.getOrDefault(game, new Stats());
                stats.put(resultSet.getString(2), resultSet.getBigDecimal(3));
                map.put(game, stats);
            }
            return map;
        } catch (SQLException e) {
            throw new IOException(e);
        }

    }

    @Override
    public void updateStatsForUser(User user, HashMap<Game, Stats> stats) throws IOException {
        for (Map.Entry<Game, Stats> stat : stats.entrySet()) {
            for (Map.Entry<String, BigDecimal> entry : stat.getValue().entrySet()) {
                try {
                    PreparedStatement check = connection.prepareStatement("SELECT 1 FROM Statistic INNER JOIN Player ON Statistic.Player_ID = Player.Player_ID WHERE Player.name = ? AND Statistic.game = ? AND Statistic.name = ?");
                    check.setString(1, user.getUsername());
                    check.setString(2, stat.getKey().toString());
                    check.setString(3, entry.getKey());
                    boolean exists = check.executeQuery().next();

                    if (exists) {
                        PreparedStatement statement = connection.prepareStatement("UPDATE Statistic INNER JOIN Player ON Statistic.Player_ID = Player.Player_ID SET value = ? WHERE Player.name = ? AND Statistic.game = ? AND Statistic.name = ?");
                        statement.setBigDecimal(1, entry.getValue());
                        statement.setString(2, user.getUsername());
                        statement.setString(3, stat.getKey().toString());
                        statement.setString(4, entry.getKey());
                        statement.execute();
                    } else {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Statistic (Player_ID, game, name, value) VALUES ((SELECT Player.Player_ID FROM Player WHERE Player.name = ? LIMIT 1), ?, ?, ?)");
                        statement.setString(1, user.getUsername());
                        statement.setString(2, stat.getKey().toString());
                        statement.setString(3, entry.getKey());
                        statement.setBigDecimal(4, entry.getValue());
                        statement.execute();
                    }

                } catch (SQLException e) {
                    throw new IOException(e);
                }
            }
        }
    }

    @Override
    public Optional<User> getUserFromAuthentication(String username, Predicate<byte[]> checkPassword) throws IOException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Player WHERE name = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() && checkPassword.test(resultSet.getBytes("hash"))) {
                return Optional.of(createUserFromResults(resultSet));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void addUser(User user) throws IOException, UserExistsException {
        try {
            PreparedStatement check = connection.prepareStatement("SELECT * FROM Player WHERE name = ?");
            check.setString(1, user.getUsername());
            if (check.executeQuery().next()) {
                throw new UserExistsException();
            }

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Player (name, hash, money, admin) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setBytes(2, user.getHash().getBytes());
            statement.setBigDecimal(3, user.getChips());
            statement.setBoolean(4, user.isAdmin());
            statement.execute();

        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void updateUser(User user) throws IOException, UserDoesNotExistException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Player SET money = ? WHERE name = ?");
            statement.setBigDecimal(1, user.getChips());
            statement.setString(2, user.getUsername());
            statement.execute();
            if (statement.getUpdateCount() == 0) {
                throw new UserDoesNotExistException();
            }

        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private User createUserFromResults(ResultSet resultSet) throws SQLException, UnsupportedEncodingException {
        return new User(resultSet.getString("name"), new String(resultSet.getBytes("hash"), "ASCII"), resultSet.getBigDecimal("money"), resultSet.getBoolean("admin"));
    }
}
