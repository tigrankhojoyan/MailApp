package com.test.mail.app.dao.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by tigran on 11/10/16.
 */
@Converter(autoApply = true)
public class PBKDF2Generator implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String password) {
        String hashedPassword = SecurityUtils.generateStrongPasswordHash(password);
        return hashedPassword;
    }

    @Override
    public String convertToEntityAttribute(String hashedPass) {
        return hashedPass;
    }


}
