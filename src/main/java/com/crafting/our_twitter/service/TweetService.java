package com.crafting.our_twitter.service;

import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;

import java.sql.Timestamp;

public class TweetService {

    private final TweetsRepository tweetsRepository;
    private final UserService userService;
    private final SwearWordsService swearWordsService;

    public TweetService(TweetsRepository tweetRepository, UserService userService, SwearWordsService swearWordsService) {
        this.tweetsRepository = tweetRepository;
        this.userService = userService;
        this.swearWordsService = swearWordsService;
    }

    public void post(Tweet tweet) {
        String userName = tweet.getUserName();
        userService.getUser(userName);

        tweet.setMessage(swearWordsService.replaceSwearWords(tweet.getMessage()));

        tweet.setTimestamp(new Timestamp(12345L));

        tweetsRepository.save(tweet);
    }

}
