package com.crafting.our_twitter.service;


import com.crafting.our_twitter.exceptions.EmptyMessageException;
import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService
{
    private final TweetsRepository tweetsRepository;

    private final UserService userService;

    public TweetService(TweetsRepository tweetsRepository, UserService userService) {
        this.tweetsRepository = tweetsRepository;
        this.userService = userService;
    }

    public void postTweet(String userName,String message)
    {

        if(message.isEmpty())  throw new EmptyMessageException();

        User user = userService.getUser(userName);

        Tweet tweet = new Tweet();
        tweet.setUserName(user.getUserName());
        tweet.setMessage(message);

        tweetsRepository.save(tweet);

    }
}
