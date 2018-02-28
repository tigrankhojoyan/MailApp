package com.test.mail.app.dao.entities;

import com.test.mail.app.dao.utils.PBKDF2Generator;
import com.test.mail.app.dao.utils.PatternConstants;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by tigran on 11/6/16.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true/*, nullable = false*/)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    @Size(min = PatternConstants.USERNAME_MIN_LENGTH, max = PatternConstants.USERNAME_MAX_LENGTH,
            message = "UserName's length must be between 6 and 14.")//TODO change messages via @java.util.ResourceBundle
    @Pattern(regexp = PatternConstants.USERNAME_PATTERN)
    private String userName;

    @Convert(converter = PBKDF2Generator.class)
    @Column(name = "PASSWORD", nullable = false)
    @NotEmpty
    /*@Size(min = PatternConstants.PASSWORD_MIN_LENGTH, max = PatternConstants.PASSWORD_MAX_LENGTH,
            message = "UserName's length must be between 6 and 14.")*/
//TODO change messages via @java.util.ResourceBundle
    /*@Pattern.List({
            @Pattern(regexp = "(?=.*[0-9])", message = "Password must contain one digit."),
            @Pattern(regexp = "(?=.*[a-z])", message = "Password must contain one lowercase letter."),
            @Pattern(regexp = "(?=.*[A-Z])", message = "Password must contain one uppercase letter."),
            @Pattern(regexp = "(?=\\S+$)", message = "Password must contain no whitespace.")
    })*/
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @ManyToMany(targetEntity = UserMusic.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "MUSIC_ITEMS",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "music_id")})
    private List<UserMusic> userMusics;

    public User() {

    }

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public User(String userName, String password, UserDetails userDetails) {
        setUserName(userName);
        setPassword(password);
//        userDetails.setUser(this);
        setUserDetails(userDetails);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public List<UserMusic> getUserMusics() {
        return userMusics;
    }

    public void setUserMusics(List<UserMusic> userMusics) {
        this.userMusics = userMusics;
    }

    public void addMusic(UserMusic music) {
        if (userMusics == null) {
            userMusics = new ArrayList<>();
        }
        userMusics.add(music);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (userDetails != null ? !userDetails.equals(user.userDetails) : user.userDetails != null) return false;
        return !(userMusics != null ? !userMusics.equals(user.userMusics) : user.userMusics != null);

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userMusics != null ? userMusics.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userDetails=" + userDetails +
                ", userMusics=" + userMusics +
                '}';
    }
}
