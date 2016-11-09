package com.test.mail.app.dao.utils;


import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.security.Key;

/**
 * Created by tigran on 11/9/16.
 */
@Converter
@PropertySource(value = { "classpath:security.properties" })
public class CryptoConverter implements AttributeConverter<String, String> {

    @Autowired
    private static Environment ENVIRONMENT;

    private static final String ALGORITHM = ENVIRONMENT.getRequiredProperty("app.algorithm");
    private static final byte[] KEY = ENVIRONMENT.getRequiredProperty("app.key").getBytes();

    @Override
    public String convertToDatabaseColumn(String ccNumber) {
        // do some encryption
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encode(c.doFinal(ccNumber.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // do some decryption
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            return new String(c.doFinal(Base64.decode(dbData)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}