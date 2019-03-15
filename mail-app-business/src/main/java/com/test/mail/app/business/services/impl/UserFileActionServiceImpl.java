package com.test.mail.app.business.services.impl;


import com.test.mail.app.business.services.UserFileActionService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;

@Service("UserFileActionService")
public class UserFileActionServiceImpl implements UserFileActionService {



    @Override
    public void uploadUserMusic(String userFolderPath, String fileOriginalName, String userName, byte[] file) throws IOException {
        if(StringUtils.isEmpty(fileOriginalName) ||
                "mp3".equalsIgnoreCase(FilenameUtils.getExtension(fileOriginalName))) {

        }
        // Save file on system
        if (!fileOriginalName.isEmpty()) {
            try (BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(userFolderPath.concat(File.separator).concat(userName),
                                    fileOriginalName)))) {
                outputStream.write(file);
            }
        }
    }

}
