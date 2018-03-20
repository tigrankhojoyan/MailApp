package com.test.mail.app.dao.entities.enums;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String role;

    private UserProfileType(String userProfileType){
        this.role = userProfileType;
    }

    public String getRole(){
        return role;
    }

}
