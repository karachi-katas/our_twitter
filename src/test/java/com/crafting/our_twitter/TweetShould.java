package com.crafting.our_twitter;


import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.service.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TweetShould {

    @Mock
    TweetsRepository tweetsRepository;

    @Test
    public  void  bePosted()
    {

        String userName = "john";
        String message = "Tweet Message";

        Tweet tweet = new Tweet();
        tweet.setUserName(userName);
        tweet.setMessage(message);

        TweetService tweetService = new TweetService(tweetsRepository);
        tweetService.post(tweet);

        verify(tweetsRepository,times(1)).save(tweet);




    }

}
