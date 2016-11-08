package com.test.mail.app.dao.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created by tigran on 11/6/16.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToOne(mappedBy="user")
    @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private UserDetails userDetails;

    public User() {

    }

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public User(String userName, String password, UserDetails userDetails) {
        setUserName(userName);
        setPassword(password);
        setUserDetails(userDetails);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!userName.equals(user.userName)) return false;
        if (!password.equals(user.password)) return false;
        return userDetails.equals(user.userDetails);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + userDetails.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}
