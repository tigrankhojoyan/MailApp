package com.test.mail.app.dao.entities;

import javax.persistence.*;

/**
 * @author tigrank
 */
@Entity
@Table(name = "user_roles")
public class UserRole {
    private Integer userRoleId;
    private String role;

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id",
            unique = true, nullable = false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
