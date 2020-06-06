package com.exhibitionCalendar.model.connection;

import com.exhibitionCalendar.util.dataBase.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLConnectionManager.class.getSimpleName());

    private static Connection connection = null;
    private static Properties properties = PropertiesManager.getDataBaseProperties("MySQL");

    private MySQLConnectionManager() {
    }

    // Connection from JDBC Driver Manager, just in case
    public static Connection getJdbcConnection() {
        LOGGER.info("getJdbcConnection start");
        try {
            LOGGER.info("Registering Driver in JDBC Driver Manager");
            Class.forName(properties.getProperty("jdbc.driver"));
            LOGGER.info("Getting connection");
            connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.user"),
                    properties.getProperty("jdbc.password"));
            LOGGER.info("Connection received and returned: " + connection);
            return connection;
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException was caught while creating a connection");
//            LOGGER.trace(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while creating a connection");
//            LOGGER.trace(e);
            e.printStackTrace();
        }
        LOGGER.warn("Smth went wrong while creating a connection to DB so the method returned null");
        return null;
    }

    // Connection from Tomcat DataSource JNDI
    public static Connection getJndiConnection() {
        LOGGER.info("getJndiConnection start");
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/expo2020_db");
            connection = dataSource.getConnection();
            LOGGER.info("Connection received");
        } catch (NamingException e) {
            LOGGER.error("NamingException was caught while getting data source from context");
//            LOGGER.trace(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error("SQLException was caught while getting connection");
//            LOGGER.trace(e);
            e.printStackTrace();
        }
        LOGGER.info("Connection received and returned: " + connection);
        return connection;
    }

    // Closing connection in case of not using "try with resources"
    public static void closeConnection() {
        LOGGER.info("closeConnection start");
        if (connection != null) {
            LOGGER.info("Connection isn't null");
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("SQLException was caught while closing a connection");
//                LOGGER.trace(e);
                e.printStackTrace();
            }
        }
    }
}
