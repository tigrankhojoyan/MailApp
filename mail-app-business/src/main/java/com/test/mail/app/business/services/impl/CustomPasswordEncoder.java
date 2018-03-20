package com.test.mail.app.business.services.impl;

import com.test.mail.app.dao.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author tigrank
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return SecurityUtils.generateStrongPasswordHash(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        try {
            return SecurityUtils.validatePassword(charSequence.toString(), s);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            return false;
        }
    }
}
