package com.crafting.our_twitter.repository.model;

import com.crafting.our_twitter.exceptions.InvalidPasswordException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    public void guardAgainstInvalidPassword(String password) {
        if (!password.equals(this.password)) {
            throw new InvalidPasswordException();
        }
    }
}
