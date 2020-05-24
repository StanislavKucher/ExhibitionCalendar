package com.exhibitionCalendar.model.dao.implementations.mySQL;

import com.exhibitionCalendar.model.connection.MySQLConnectionManager;
import com.exhibitionCalendar.model.dao.interfaces.UserDAO;
import com.exhibitionCalendar.model.entities.Role;
import com.exhibitionCalendar.model.entities.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

public class MySQLUserDAO implements UserDAO<User, Integer> {

    private static final Logger LOGGER = Logger.getLogger(MySQLUserDAO.class.getSimpleName());

    private Properties properties;

    public MySQLUserDAO(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean create(User user) {
        LOGGER.info("Method Create user starts with a user: " + user);
        boolean result = false;
        LOGGER.info("Query: " + properties.getProperty("insert"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("insert"))) {
            setPreparedStatement(statement, user);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while adding a new user to DB" + e);
            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("User was added to DB successfully: " + result);
        return result;
    }

    @Override
    public User retrieve(Integer userId) {
        LOGGER.info("Method retrieve user starts with a userId: " + userId);
        User user = null;
        LOGGER.info("Query: " + properties.getProperty("select"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("select"))) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while retrieving user info from DB" + e);
            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("Method retrieve returns the user: " + user);
        return user;
    }

    @Override
    public boolean update(User user) {
        LOGGER.info("Method update user starts with a user: " + user);
        boolean result = false;
        LOGGER.info("Query: " + properties.getProperty("update"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("update"))) {
            setPreparedStatement(statement, user);
            statement.setInt(8, user.getUserID());
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while updating user info in DB " + e);
            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("The user info was successfully updated: " + result);
        return result;
    }

    @Override
    public boolean delete(Integer userId) {
        LOGGER.info("Method delete user starts with a userId" + userId);
        boolean result = false;
        LOGGER.info("Query: " + properties.getProperty("delete"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("delete"))) {
            statement.setInt(1, userId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while deleting a user from DB" + e);
            e.printStackTrace();
        }
        LOGGER.info("A user was successfully deleted: " + result);
        return result;
    }

    @Override
    public User retrieveByLogin(String login) {
        LOGGER.info("Method retrieveByLogin user starts with a login: " + login);
        User user = null;
        LOGGER.info("Query: " + properties.getProperty("selectByEmail"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("selectByLogin"))) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while getting user info by login from DB" + e);
            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("Method retrieveByLogin returns the user: " + user);
        return user;
    }

    // Supplementary method
    private void setPreparedStatement(PreparedStatement statement, User user) throws SQLException {
        LOGGER.info("Method setPreparedStatement starts with parameters: statement: " + statement + ". And user: " + user);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getLogin());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getPhone());
        statement.setString(6, user.getRole().name().toLowerCase(Locale.ENGLISH));
        statement.setString(7, user.getEmail());
        LOGGER.info("SetPreparedStatement ends with parameters: statement: " + statement + ". And user: " + user);
    }

    // Supplementary method
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        LOGGER.info("Method getUserFromResultSet starts with resultSet: " + resultSet);
        return new User.Builder()
                .setUserID(resultSet.getInt("account_id"))
                .setFirstName(resultSet.getString("first_name"))
                .setLastName(resultSet.getString("last_name"))
                .setLogin(resultSet.getString("login"))
                .setPassword(resultSet.getString("password"))
                .setPhone(resultSet.getString("phone"))
                .setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ENGLISH)))
                .setEmail(resultSet.getString("email"))
                .build();
    }
}
