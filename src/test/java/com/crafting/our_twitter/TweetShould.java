package com.crafting.our_twitter;

import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.repository.model.User;
import com.crafting.our_twitter.service.TweetService;
import com.crafting.our_twitter.service.UserManagementService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static com.crafting.our_twitter.UserShould.USERNAME;
import static com.crafting.our_twitter.UserShould.getUser;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TweetShould {

    @Mock
    TweetsRepository tweetsRepository;

    @Mock
    UserManagementService userManagementService;

    @InjectMocks
    TweetService tweetService;

    @Test
    public void postByUser() {

        // SETUP
        String message = "message";
        User user = getUser();
        when(userManagementService.getUser(USERNAME)).thenReturn(user);

        // ACTION
        Integer tweetId =  tweetService.postTweet(USERNAME, message);

        // ASSERTION
        ArgumentCaptor<Tweet> tweetCaptor = ArgumentCaptor.forClass(Tweet.class);
        verify(tweetsRepository, times(1)).save(tweetCaptor.capture());
        verify(userManagementService, times(1)).getUser(USERNAME);

        Assert.assertEquals( tweetId,tweetCaptor.getValue().getId());
        Assert.assertEquals( message,tweetCaptor.getValue().getMessage());
    }
}
