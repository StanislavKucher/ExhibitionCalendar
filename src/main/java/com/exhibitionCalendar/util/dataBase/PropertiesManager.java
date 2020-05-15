package com.exhibitionCalendar.util.dataBase;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private static final Logger LOGGER = Logger.getLogger(PropertiesManager.class.getSimpleName());
    private static final String MYSQL_PROPERTIES_URL = "src/main/resources/mySQLdb.properties";

    private static Properties properties = null;

    private PropertiesManager() {
    }

    public static Properties getDataBaseProperties(String dbName) {
        LOGGER.info("getDataBaseProperties start");
        if (dbName == null) {
            LOGGER.debug("Parameter dbName was null");
            throw new NullPointerException();
        }
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

        if (properties != null) {
            LOGGER.info("Properties loaded and returned: " + properties);
            return properties;
        }

        LOGGER.warn("Smth went wrong while getting and loading properties for DB so the method returned null");
        return null;
    }
}
