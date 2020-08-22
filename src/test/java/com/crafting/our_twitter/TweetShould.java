package com.crafting.our_twitter;

import static com.crafting.our_twitter.fixtures.UserFixture.USERNAME;
import static com.crafting.our_twitter.fixtures.UserFixture.getUser;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Integer tweetId = tweetService.postTweet(USERNAME, message);

        // ASSERTION
        ArgumentCaptor<Tweet> tweetCaptor = ArgumentCaptor.forClass(Tweet.class);
        verify(tweetsRepository, times(1)).save(tweetCaptor.capture());
        verify(userManagementService, times(1)).getUser(USERNAME);

        Assert.assertEquals(tweetId, tweetCaptor.getValue().getId());
        Assert.assertEquals(message, tweetCaptor.getValue().getMessage());
        Assert.assertEquals(USERNAME, tweetCaptor.getValue().getUserName());
    }

    @Test
    public void postByUserHavingSwearWord() {

        // SETUP
        String message = "hello stupid";
        User user = getUser();
        when(userManagementService.getUser(USERNAME)).thenReturn(user);

        // ACTION
        Integer tweetId = tweetService.postTweet(USERNAME, message);

        // ASSERTION
        ArgumentCaptor<Tweet> tweetCaptor = ArgumentCaptor.forClass(Tweet.class);
        verify(tweetsRepository, times(1)).save(tweetCaptor.capture());
        verify(userManagementService, times(1)).getUser(USERNAME);

        Assert.assertEquals(tweetId, tweetCaptor.getValue().getId());
        Assert.assertEquals("hello duck", tweetCaptor.getValue().getMessage());
        Assert.assertEquals(USERNAME, tweetCaptor.getValue().getUserName());
    }

    @Test
    public void postByUserHavingMultipleSwearWords() {

        // SETUP
        String message = "hello stupid and mad and dog";
        User user = getUser();
        when(userManagementService.getUser(USERNAME)).thenReturn(user);

        // ACTION
        Integer tweetId = tweetService.postTweet(USERNAME, message);

        // ASSERTION
        ArgumentCaptor<Tweet> tweetCaptor = ArgumentCaptor.forClass(Tweet.class);
        verify(tweetsRepository, times(1)).save(tweetCaptor.capture());
        verify(userManagementService, times(1)).getUser(USERNAME);

        Assert.assertEquals(tweetId, tweetCaptor.getValue().getId());
        Assert.assertEquals("hello duck and duck and duck", tweetCaptor.getValue().getMessage());
        Assert.assertEquals(USERNAME, tweetCaptor.getValue().getUserName());
    }
}
