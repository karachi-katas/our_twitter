package com.crafting.our_twitter;

import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.service.SwearWordsService;
import com.crafting.our_twitter.service.TweetService;
import com.crafting.our_twitter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetShould {

    @Mock
    TweetsRepository tweetsRepository;

    @Mock
    UserService userService;

    @Mock
    SwearWordsService swearWordsService;

    private TweetService tweetService;

    @Before
    public void setup()
    {
        tweetService = new TweetService(tweetsRepository, userService, swearWordsService);
    }

    @Test
    public void beAbleToPost()
    {
        //Setup
        Tweet tweet= new Tweet();

        //Action
        tweetService.post(tweet);

        //Assertion
        verify(tweetsRepository,times(1)).save(tweet);
    }

    @Test
    public void bePostedWithCorrectTimeStamp()
    {
        // Setup
        Tweet tweet=new Tweet();
        Timestamp timestamp = new Timestamp(12345L);
        ArgumentCaptor<Tweet> captor = ArgumentCaptor.forClass(Tweet.class);

        // Action
        tweetService.post(tweet);

        // Assertion
        verify(tweetsRepository, times(1)).save(captor.capture());
        Tweet actual = captor.getValue();
        assertEquals(actual.getTimestamp(), timestamp);
    }

    @Test(expected = UserNotFoundException.class)
    public void notBePostedWithInvalidUsername() {
        // Setup
        Tweet tweet = new Tweet();
        String INVALID_USERNAME = "INVALID";

        tweet.setUserName(INVALID_USERNAME);

        when(userService.getUser(INVALID_USERNAME)).thenThrow(UserNotFoundException.class);

        // Action
        tweetService.post(tweet);

        // Assertion

    }

    @Test
    public void bePostedWithSwearWordsReplacedWithDuck() {
        // Setup
        Tweet tweet = new Tweet();
        String message = "Shit Okay Not a Effing Swear Words Damn";
        String cleanedMessage = "duck Okay Not a duck Swear Words duck";
        tweet.setMessage(message);

        ArgumentCaptor<Tweet> captor = ArgumentCaptor.forClass(Tweet.class);
        when(swearWordsService.replaceSwearWords(message)).thenReturn(cleanedMessage);
        when(swearWordsService.getSwearWords()).thenReturn(Arrays.asList( "Shit", "Effing", "Damn" ));

        // Action
        tweetService.post(tweet);

        // Assertion
        verify(tweetsRepository, times(1)).save(captor.capture());
        Tweet actual = captor.getValue();

        assertEquals("duck Okay Not a duck Swear Words duck", actual.getMessage());
    }
}
