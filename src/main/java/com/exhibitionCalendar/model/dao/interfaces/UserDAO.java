package com.exhibitionCalendar.model.dao.interfaces;

public interface UserDAO<User, Integer> extends BaseDAO<User, Integer> {

    User retrieveByLogin(String login);

}
