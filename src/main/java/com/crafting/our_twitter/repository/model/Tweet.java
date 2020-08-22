package com.crafting.our_twitter.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "tweet")
public class Tweet {

    @Id
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    static SwearWords swearWord = new SwearWords();
    public Tweet(int id, String userName, String message) {

        this.id = id;
        this.userName = userName;
        this.message = swearWord.censor(message);

    }




}

