package com.test.mail.app.dao.entities;

import com.test.mail.app.dao.entities.enums.Gender;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by tigran on 11/6/16.
 */
@Entity
@Table(name="USER_DETAILS")
public class UserDetails implements Serializable {

//    @GenericGenerator(name = "generator", strategy = "foreign",
//            parameters = @Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//(generator = "generator")
    @Column(name = "USER_DATA_ID", unique = true/*, nullable = false*/)
    private Long userDataId;

   /* @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;*/

    @Column(name = "BIRTH_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthDate;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public UserDetails() {
    }

    public UserDetails(LocalDate birthDate, Gender gender) {
        setBirthDate(birthDate);
        setGender(gender);
    }

/*    public UserDetails(LocalDate birthDate, Gender gender, User user) {
        setBirthDate(birthDate);
        setGender(gender);
        setUser(user);
    }*/

    public long getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(long userDataId) {
        this.userDataId = userDataId;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (userDataId != null ? !userDataId.equals(that.userDataId) : that.userDataId != null) return false;
//        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        return gender == that.gender;

    }

    @Override
    public int hashCode() {
        int result = userDataId != null ? userDataId.hashCode() : 0;
//        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userDataId=" + userDataId +
//                ", user=" + user.getUserName() +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                '}';
    }
}
