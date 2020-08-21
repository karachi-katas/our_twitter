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
    private String id;

    @Column(name = "message")
    private String message;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "timestamp")
    private Timestamp timestamp;

}

