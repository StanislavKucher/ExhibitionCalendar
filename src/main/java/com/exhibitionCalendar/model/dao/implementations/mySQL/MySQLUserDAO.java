package com.exhibitionCalendar.model.dao.implementations.mySQL;

import com.exhibitionCalendar.model.dao.interfaces.UserDAO;
import com.exhibitionCalendar.model.entities.User;
import org.apache.log4j.Logger;

import java.util.Properties;

public class MySQLUserDAO implements UserDAO<User, Integer> {

    private static final Logger LOGGER = Logger.getLogger(MySQLUserDAO.class.getSimpleName());

    private Properties properties;

    public MySQLUserDAO(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public User retrieve(Integer userId) {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(Integer userId) {
        return false;
    }

    @Override
    public User retrieveByLogin(String login) {
        return null;
    }

}
