package com.crafting.our_twitter.repository.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "like")
public class Like {
    @Id
    private Integer id;

    @Column(name = "tweet_id")
    private Integer tweetId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "reaction_enum")
    private Integer reactionEnum;

}

