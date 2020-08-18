package com.crafting.our_twitter.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "hashtags")
public class Hashtags {

    @Id
    private String id;

    @Column(name = "tweet_id")
    private Integer tweet_id;

    @Column(name = "hashtag")
    private String hashtag;
}
