package com.exhibitionCalendar.model.dao.implementations.mySQL;

import com.exhibitionCalendar.model.dao.interfaces.CreditCardDAO;
import com.exhibitionCalendar.model.entities.CreditCard;
import org.apache.log4j.Logger;

import java.util.Properties;


public class MySQLCreditCardDAO implements CreditCardDAO<CreditCard, Integer> {

    private static final Logger LOGGER = Logger.getLogger(MySQLCreditCardDAO.class.getSimpleName());

    private Properties properties;

    public MySQLCreditCardDAO(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean create(CreditCard creditCard) {
        return false;
    }

    @Override
    public CreditCard retrieve(Integer creditCardId) {
        return null;
    }

    @Override
    public boolean update(CreditCard creditCard) {
        return false;
    }

    @Override
    public boolean delete(Integer creditCardId) {
        return false;
    }

    @Override
    public CreditCard retrieveByUserId(Integer userId) {
        return null;
    }

}
