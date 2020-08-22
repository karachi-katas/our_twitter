package com.crafting.our_twitter.service;

import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TweetService {


    @Autowired
    TweetsRepository tweetsRepository;

    public TweetService(TweetsRepository tweetRepository) {

        this.tweetsRepository = tweetRepository;
    }

    public void post(Tweet tweet) {

        tweetsRepository.save(tweet);


    }
}
