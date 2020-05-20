package com.exhibitionCalendar.util.dataBase;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * To receive properties that contain DB settings and SQL queries for different DB
 *
 * @author Stanislav Kucher
 * @version 1.0
 */
public class PropertiesManager {

    private static final Logger LOGGER = Logger.getLogger(PropertiesManager.class.getSimpleName());

    private static final String MYSQL_PROPERTIES_URL = "src/main/resources/mySQLdb.properties";
    private static final String QUERIES_PROPERTIES_URL = "database/mySQL/queries/";

    private PropertiesManager() {
    }

    /**
     * The method loads and returns properties with DB JDBC settings
     *
     * @param dbName data base's name that is chosen as a source of data
     * @return properties with DB JDBC settings
     */

    public static Properties getDataBaseProperties(String dbName) {
        LOGGER.info("getDataBaseProperties start");

        if (dbName == null || dbName.trim().isEmpty()) {
            LOGGER.debug("Method parameter dbName is invalid");
            throw new NullPointerException();
        }

        Properties properties = new Properties();

        switch (dbName) {
            case "MySQL":
                try {
                    LOGGER.info("Reading properties file for MySQL");
                    FileInputStream input = new FileInputStream(MYSQL_PROPERTIES_URL);
                    LOGGER.info("Loading properties from file");
                    properties.load(input);
                } catch (FileNotFoundException e) {
                    LOGGER.error("Properties file for MySQL wasn't found");
                    LOGGER.trace(e);
                    e.printStackTrace();
                } catch (IOException e) {
                    LOGGER.error("IOException was caught while loading properties from file");
                    LOGGER.trace(e);
                    e.printStackTrace();
                }
                break;
            case "PostgreSQL":
                // when added
                break;
            default:
                LOGGER.error("Wrong parameter was passed to the method");
                throw new IllegalArgumentException();
        }

        LOGGER.info("DB properties loaded and returned: " + properties);
        return properties;
    }

    /**
     * The method loads and returns properties with queries
     * for DB from a properties file
     *
     * @param dbName     data base's name that is chosen as a source of data
     * @param entityName entities's name that the queries are required for
     * @return properties with CRUD and other queries
     */
    public static Properties getQueryProperties(String dbName, String entityName) {

        LOGGER.info("getQueryProperties starts to find property for " + dbName + " DB and " + entityName + " entity");

        if (dbName == null || dbName.trim().isEmpty()) {
            LOGGER.debug("Method parameter dbName is invalid");
            throw new IllegalArgumentException();
        }

        if (entityName == null || entityName.trim().isEmpty()) {
            LOGGER.debug("Method parameter entityName is invalid");
            throw new IllegalArgumentException();
        }

        Properties properties = new Properties();

        switch (dbName) {
            case "MySQL":
                try {
                    LOGGER.info("Reading query properties file for MySQL");
                    String pathToQueryProperties = QUERIES_PROPERTIES_URL + entityName + "/queries.properties";
                    LOGGER.info("Built path to query properties: " + pathToQueryProperties);
                    FileInputStream input = new FileInputStream(pathToQueryProperties);
                    LOGGER.info("Loading query properties from file");
                    properties.load(input);
                } catch (FileNotFoundException e) {
                    LOGGER.error("Query properties file for MySQL wasn't found");
                    LOGGER.trace(e);
                    e.printStackTrace();
                } catch (IOException e) {
                    LOGGER.error("IOException was caught while loading query properties from file");
                    LOGGER.trace(e);
                    e.printStackTrace();
                }
                break;
            case "PostgreSQL":
                // when added
                break;
            default:
                LOGGER.error("Wrong parameter was passed to the method");
                throw new IllegalArgumentException();
        }

        LOGGER.info("Query properties loaded and returned: " + properties);
        return properties;
    }

}
