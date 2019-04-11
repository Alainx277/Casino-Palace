package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DatabaseStorage implements Storage {
    private Connection connection;

    public DatabaseStorage(String endpoint, String dbname, String user, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + endpoint + "/" + dbname, user, password);
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
    public Stats getStatsForUser(User user) {
        throw new NotImplementedException();
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
            if (statement.getUpdateCount() == 0){
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
