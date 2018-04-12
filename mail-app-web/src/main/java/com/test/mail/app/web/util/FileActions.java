package com.test.mail.app.web.util;

import com.test.mail.app.web.exceptions.MailWebException;

import java.io.File;
import java.io.IOException;

public final class FileActions {

    public static void createUserFolder(String folderDestination, String folderName) throws MailWebException {
        File folder = new File(folderDestination, folderName);
        if (!folder.mkdirs()) {
            throw new MailWebException("Unable to create user folder");
        }
    }

}
