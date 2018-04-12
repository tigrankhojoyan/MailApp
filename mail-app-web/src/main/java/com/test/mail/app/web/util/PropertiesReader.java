package com.test.mail.app.web.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropertiesReader {

    private static Properties USER_PROPERTIES = new Properties();

    public PropertiesReader() {
        try(InputStream userInput = getClass().getClassLoader().getResourceAsStream("user.properties")) {
            USER_PROPERTIES.load(userInput);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Properties getUserProperties() {
        return USER_PROPERTIES;
    }
}
