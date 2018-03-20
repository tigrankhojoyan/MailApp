package com.test.mail.app.dao.entities;

import com.test.mail.app.dao.entities.enums.UserProfileType;

import javax.persistence.*;

/**
 * @author tigrank
 */
@Entity
@Table(name = "user_roles")
public class UserRole {
    private Integer userRoleId;
    private String role = UserProfileType.USER.getRole();

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name = "role", nullable = false, length = 45, unique=true)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
