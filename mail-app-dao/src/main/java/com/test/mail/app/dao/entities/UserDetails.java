package com.test.mail.app.dao.entities;

import com.test.mail.app.dao.entities.enums.Gender;
import org.hibernate.annotations.*;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tigran on 11/6/16.
 */
@Entity
@Table(name="USER_DETAILS")
public class UserDetails {

    @Id
    @Column(name="usr_id", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign",
            parameters = {@org.hibernate.annotations.Parameter(name="property", value="user")})
    private long userDataId;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @Column(name = "BIRTH_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthDate;

    @Column(name = "GENDER")
    private Gender gender;

    public UserDetails() {

    }

    public UserDetails(LocalDate birthDate, Gender gender) {
        setBirthDate(birthDate);
        setGender(gender);
    }

    public long getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(long userDataId) {
        this.userDataId = userDataId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

        if (userDataId != that.userDataId) return false;
        if (!user.equals(that.user)) return false;
        if (!birthDate.equals(that.birthDate)) return false;
        return gender == that.gender;

    }

    @Override
    public int hashCode() {
        int result = (int) (userDataId ^ (userDataId >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userDataId=" + userDataId +
                ", user=" + user +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                '}';
    }
}
