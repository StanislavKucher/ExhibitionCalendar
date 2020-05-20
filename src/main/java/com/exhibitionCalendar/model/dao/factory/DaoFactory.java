package com.exhibitionCalendar.model.dao.factory;

import com.exhibitionCalendar.model.dao.implementations.mySQL.*;
import com.exhibitionCalendar.model.dao.interfaces.*;
import com.exhibitionCalendar.util.dataBase.PropertiesManager;

public class DaoFactory {

    public static UserDAO getUserDAO(String dbName) {
        if (dbName.equals("MySQL")) {
            return new MySQLUserDAO(PropertiesManager.getQueryProperties("MySQL", "user"));
        } else if (dbName.equals("PostgreSQL")) {
            // when added
            return null;
        } else {
            // when added another source of data
            return null;
        }
    }

    public static CreditCardDAO getCreditCardDAO(String dbName) {
        if (dbName.equals("MySQL")) {
            return new MySQLCreditCardDAO(PropertiesManager.getQueryProperties("MySQL", "creditCard"));
        } else if (dbName.equals("PostgreSQL")) {
            // when added
            return null;
        } else {
            // when added another source of data
            return null;
        }
    }

}
