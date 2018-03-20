package com.test.mail.app.dao.entities;

import com.test.mail.app.dao.utils.PBKDF2Generator;
import com.test.mail.app.dao.utils.PatternConstants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by tigran on 11/6/16.
 */
@Entity
@Table(name="users")
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
            message = "UserName's length must be between 6 and 14.")*///TODO change messages via @java.util.ResourceBundle
    /*@Pattern.List({
            @Pattern(regexp = "(?=.*[0-9])", message = "Password must contain one digit."),
            @Pattern(regexp = "(?=.*[a-z])", message = "Password must contain one lowercase letter."),
            @Pattern(regexp = "(?=.*[A-Z])", message = "Password must contain one uppercase letter."),
            @Pattern(regexp = "(?=\\S+$)", message = "Password must contain no whitespace.")
    })*/
    private String password;

    @Column(name = "FIRST_NAME")
    @Pattern(regexp = PatternConstants.NAME_SURNAME_PATTERN)
    private String firstName;

    @Column(name = "LAST_NAME")
    @Pattern(regexp = PatternConstants.NAME_SURNAME_PATTERN)
    private String lastName;
    
    @ManyToMany(targetEntity = UserMusic.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "MUSIC_ITEMS",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "music_id") })
    private List<UserMusic> userMusics;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_ROLES",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_role_id") })
    private Set<UserRole> userRole = new HashSet<UserRole>();

    public User() {

    }

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public User(String userName, String password, String firstName, String lastName) {
        setUserName(userName);
        setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String userName, String password, UserDetails userDetails) {
        setUserName(userName);
        setPassword(password);
//        userDetails.setUser(this);
        setUserDetails(userDetails);
    }

    public User(String userName, String password, UserDetails userDetails, String firstName, String lastName) {
        setUserName(userName);
        setPassword(password);
        this.lastName = lastName;
        this.firstName = firstName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (!userName.equals(user.userName)) return false;
        if (!password.equals(user.password)) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return !(userDetails != null ? !userDetails.equals(user.userDetails) : user.userDetails != null);

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userDetails != null ? userDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userDetails=" + userDetails + '\'' +
                ", userMusics=" + userMusics + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
