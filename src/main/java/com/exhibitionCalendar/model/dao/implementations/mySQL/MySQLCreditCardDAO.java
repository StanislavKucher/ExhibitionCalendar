package com.exhibitionCalendar.model.dao.implementations.mySQL;

import com.exhibitionCalendar.model.connection.MySQLConnectionManager;
import com.exhibitionCalendar.model.dao.interfaces.CreditCardDAO;
import com.exhibitionCalendar.model.entities.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLCreditCardDAO implements CreditCardDAO<CreditCard, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLCreditCardDAO.class.getSimpleName());

    private Properties properties;

    public MySQLCreditCardDAO(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean create(CreditCard creditCard) {
        LOGGER.info("Method create creditCard starts with a creditCard: " + creditCard);
        boolean result = false;
        LOGGER.info("Query: " + properties.getProperty("insert"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("insert"))) {
            setPreparedStatement(statement, creditCard);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while adding a new creditCard to DB" + e);
//            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("CreditCard was added to DB successfully: " + result);
        return result;
    }

    @Override
    public CreditCard retrieve(Integer creditCardId) {
        LOGGER.info("Method retrieve creditCard starts with a creditCardId: " + creditCardId);
        CreditCard creditCard = null;
        LOGGER.info("Query: " + properties.getProperty("select"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("select"))) {
            statement.setInt(1, creditCardId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                creditCard = getCreditCardFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while retrieving creditCard info from DB" + e);
//            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("Method retrieve returns the creditCard: " + creditCard);
        return creditCard;
    }

    @Override
    public boolean update(CreditCard creditCard) {
        LOGGER.info("Method update creditCard starts with a creditCard: " + creditCard);
        boolean result = false;
        LOGGER.info("Query: " + properties.getProperty("update"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("update"))) {
            setPreparedStatement(statement, creditCard);
            statement.setInt(5, creditCard.getCreditCardID());
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while updating creditCard info in DB " + e);
//            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("The creditCard info was successfully updated: " + result);
        return result;
    }

    @Override
    public boolean delete(Integer creditCardId) {
        LOGGER.info("Method delete creditCard starts with a creditCardId" + creditCardId);
        boolean result = false;
        LOGGER.info("Query: " + properties.getProperty("delete"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("delete"))) {
            statement.setInt(1, creditCardId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while deleting a creditCard from DB" + e);
            e.printStackTrace();
        }
        LOGGER.info("A creditCard was successfully deleted: " + result);
        return result;
    }

    // The current method's implementation is in progress. Might be conveyed to UserDAO and it's impls.
    @Override
    public CreditCard retrieveByUserId(Integer userId) {
        return null;
    }

    @Override
    public double getBalanceByCardId(Integer creditCardId) {
        LOGGER.info("Method getBalanceByCardId starts with a creditCardId: " + creditCardId);
        double result = 0.0;
        LOGGER.info("Query: " + properties.getProperty("getBalanceByCardId"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("getBalanceByCardId"))) {
            statement.setInt(1, creditCardId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                result = resultSet.getBigDecimal("balance").doubleValue();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while getting card's balance from DB" + e);
//            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("Method getBalanceByCardId returns the card's balance: " + result);
        return result;
    }

    @Override
    public boolean updateBalance(Integer creditCardId, double newBalance) {
        LOGGER.info("Method updateBalance starts with a creditCardId: " + creditCardId + " and new balance value: " + newBalance);
        boolean result = false;
        LOGGER.info("Query: " + properties.getProperty("updateBalance"));
        try (Connection connection = MySQLConnectionManager.getJndiConnection();
             PreparedStatement statement = connection.prepareStatement(properties.getProperty("updateBalance"))) {
            statement.setBigDecimal(1, BigDecimal.valueOf(newBalance));
            statement.setInt(2, creditCardId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while updating credit card's balance info in DB " + e);
//            LOGGER.error(e);
            e.printStackTrace();
        }
        LOGGER.info("The credit card's balance was successfully updated: " + result);
        return result;
    }

    // Supplementary method
    private void setPreparedStatement(PreparedStatement statement, CreditCard creditCard) throws SQLException {
        LOGGER.info("Method setPreparedStatement starts with parameters: statement: " + statement + ". And user: " + creditCard);
        statement.setString(1, creditCard.getNumber());
        statement.setInt(2, creditCard.getCVV());
        /* Default value is set up in the DB when the card is created but just in case
        Might need refactor Entities' (CreditCard & Exposition) fields from double to BigDecimal at a later date
        statement.setDouble(3,1000.00);
        statement.setBigDecimal(3, BigDecimal.valueOf(1000.00).setScale(2, RoundingMode.DOWN));*/
        statement.setInt(3, creditCard.getMonth());
        statement.setInt(4, creditCard.getYear());
        LOGGER.info("SetPreparedStatement ends with parameters: statement: " + statement + ". And user: " + creditCard);
    }

    // Supplementary method
    private CreditCard getCreditCardFromResultSet(ResultSet resultSet) throws SQLException {
        LOGGER.info("Method getCreditCardFromResultSet starts with resultSet: " + resultSet);
        return new CreditCard.Builder()
                .setCreditCardID(resultSet.getInt("card_id"))
                .setNumber(resultSet.getString("card_number"))
                .setCVV(resultSet.getInt("cvv"))
                // Might need refactor Entities' (CreditCard & Exposition) fields from double to BigDecimal at a later date
                .setBalance(resultSet.getBigDecimal("balance").doubleValue())
                .setMonth(resultSet.getInt("expire_month"))
                .setYear(resultSet.getInt("expire_year"))
                .setUserID(resultSet.getInt("account_id"))
                .build();
    }
}