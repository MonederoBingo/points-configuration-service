package com.monederobingo.points_configuration.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager
{
    private static final String PROPERTY_FILE_NAME = "leal.properties";
    private static Properties _properties = null;

    private PropertyManager() {
    }

    public static void loadConfiguration() {
        if (_properties == null) {
            reloadConfiguration();
        }
    }

    public static void reloadConfiguration() {
        if (_properties == null) {
            _properties = new Properties();
        }
        InputStream inputStream = PropertyManager.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
        if (inputStream != null) {
            try {
                _properties.load(inputStream);
            } catch (IOException e) {
                final String error = "Could not load property file: " + PROPERTY_FILE_NAME;
                throw new RuntimeException(error);
            }
        } else {
            final String error = "Could not find property file: " + PROPERTY_FILE_NAME;
            throw new RuntimeException(error);
        }
    }

    public static Properties getProperties() {
        if (_properties == null) {
            PropertyManager.loadConfiguration();
        }
        return _properties;
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}
