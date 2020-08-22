package com.crafting.our_twitter.service;


import com.crafting.our_twitter.exceptions.EmptyMessageException;
import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import org.springframework.stereotype.Service;

@Service
public class TweetService
{
    private final TweetsRepository tweetsRepository;

    public TweetService(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    public void postTweet(String userName,String message)
    {
        Tweet tweet = new Tweet();
        tweet.setUserName(userName);
        tweet.setMessage(message);

        if(message.isEmpty())
            throw new EmptyMessageException();

        tweetsRepository.save(tweet);

    }
}
