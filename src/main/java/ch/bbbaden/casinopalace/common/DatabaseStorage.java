package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.function.Predicate;

public class DatabaseStorage implements Storage {
    private Connection connection;

    public DatabaseStorage(String endpoint, String dbname, String user, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + endpoint + "/" + dbname + "?createDatabaseIfNotExist=true", user, password);

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
    public List<Stats> getStatsForUser(User user) throws IOException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT statistic.game, statistic.name, statistic.value FROM statistic INNER JOIN player ON statistic.Player_ID = player.Player_ID WHERE player.name = ? ORDER BY statistic.game");
            statement.setString(1, user.getUsername());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Stats> stats = new ArrayList<>();
            while (resultSet.next()) {
                Game game = Game.valueOf(resultSet.getString(1));
                Stats stat = stats.stream().filter(x -> x.getGame() == game).findFirst().orElse(new Stats(game));
                if (!stats.contains(stat)) {
                    stats.add(stat);
                }

                stat.getValues().put(resultSet.getString(2), resultSet.getBigDecimal(3));
            }
            return stats;
        } catch (SQLException e) {
            throw new IOException(e);
        }

    }

    @Override
    public void updateStatsForUser(User user, List<Stats> stats) throws IOException {
        for (Stats stat : stats) {
            for (Map.Entry<String, BigDecimal> entry : stat.getValues().entrySet()) {
                try {
                    PreparedStatement check = connection.prepareStatement("SELECT 1 FROM statistic INNER JOIN player ON statistic.Player_ID = player.Player_ID WHERE player.name = ? AND statistic.game = ? AND statistic.name = ?");
                    check.setString(1, user.getUsername());
                    check.setString(2, stat.getGame().toString());
                    check.setString(3, entry.getKey());
                    boolean exists = check.executeQuery().next();

                    if (exists) {
                        PreparedStatement statement = connection.prepareStatement("UPDATE statistic SET value = ? FROM statistic INNER JOIN player ON statistic.Player_ID = player.Player_ID WHERE player.name = ? AND statistic.game = ? AND statistic.name = ?");
                        statement.setBigDecimal(1, entry.getValue());
                        statement.setString(2, user.getUsername());
                        statement.setString(3, stat.getGame().toString());
                        statement.setString(4, entry.getKey());
                        statement.execute();
                    } else {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO statistic (Player_ID, game, name, value) VALUES ((SELECT player.Player_ID FROM player WHERE player.name = ? LIMIT 1), ?, ?, ?)");
                        statement.setString(1, user.getUsername());
                        statement.setString(2, stat.getGame().toString());
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
