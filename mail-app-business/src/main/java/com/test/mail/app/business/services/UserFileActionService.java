package com.test.mail.app.business.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserFileActionService {

    void uploadUserMusic(String userFolderPath, String fileOriginalName, String userName, byte[] file) throws IOException;

}
