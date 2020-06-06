package com.exhibitionCalendar.service.implementations;

import com.exhibitionCalendar.model.dao.factory.DaoFactory;
import com.exhibitionCalendar.model.dao.interfaces.UserDAO;
import com.exhibitionCalendar.model.entities.User;
import com.exhibitionCalendar.service.interfaces.UserService;
import com.exhibitionCalendar.util.RequestParametersManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.getSimpleName());
    private UserDAO<User, Integer> userDao = DaoFactory.getUserDAO("MySQL");

    @Override
    public User createNewUser(Map<String, String> reqParams) {
        LOGGER.info("Method createNewUser starts with the following request params {}", reqParams);

        if (userDao.retrieveByLogin(reqParams.get("login")) != null) {
            LOGGER.info("Such user already exists");
            return null;
        }
        LOGGER.info("It's a new user");

        User user = RequestParametersManager.buildUserFromValidReqParams(reqParams);

        userDao.create(user);

        // Getting a the same user from DB but with already assigned ID number
        user = userDao.retrieveByLogin(user.getLogin());

        LOGGER.debug("The method createNewUser returns a new user {}", user);
        return user;
    }
}
