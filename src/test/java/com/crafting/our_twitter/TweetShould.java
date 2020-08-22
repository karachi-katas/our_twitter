package com.crafting.our_twitter;

import com.crafting.our_twitter.exceptions.EmptyMessageException;
import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.service.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TweetShould {

    @Mock
    TweetsRepository tweetsRepository;

    @Test(expected = EmptyMessageException.class)
    public void notBeEmpty(){
        String message = "";
        String userName = "dummy";

        TweetService tweetService = new TweetService(tweetsRepository);
        tweetService.postTweet(userName,message);
    }
}
