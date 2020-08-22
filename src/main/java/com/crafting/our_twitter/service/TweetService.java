package com.crafting.our_twitter.service;

import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;

import java.sql.Timestamp;

public class TweetService {

    private final TweetsRepository tweetsRepository;

    public TweetService(TweetsRepository tweetRepository) {
        this.tweetsRepository = tweetRepository;
    }

    public void post(Tweet tweet) {
        tweet.setTimestamp(new Timestamp(12345L));
        tweetsRepository.save(tweet);
    }
}
