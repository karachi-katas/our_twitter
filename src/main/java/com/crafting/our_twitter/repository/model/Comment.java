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
@Table(name = "comment")
public class Comment {

    @Id
    private String id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "tweet_id")
    private Integer tweetId;

    @Column(name = "timestamp")
    private Timestamp timestamp;

}
