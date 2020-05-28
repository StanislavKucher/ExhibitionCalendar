package com.exhibitionCalendar.service.implementations;

import com.exhibitionCalendar.model.dao.factory.DaoFactory;
import com.exhibitionCalendar.model.dao.interfaces.UserDAO;
import com.exhibitionCalendar.model.entities.User;
import com.exhibitionCalendar.service.interfaces.UserService;
import org.apache.log4j.Logger;

import java.util.Map;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getSimpleName());
    private UserDAO<User, Integer> userDao = DaoFactory.getUserDAO("MySQL");

    @Override
    public String createNewUser(Map<String, String> reqParams) {

        return "";
    }
}
