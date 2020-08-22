package com.crafting.our_twitter;

import com.crafting.our_twitter.exceptions.EmptyMessageException;
import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.service.TweetService;
import com.crafting.our_twitter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetShould {

    @Mock
    TweetsRepository tweetsRepository;

    @Mock
    UserService userService;

    @Test(expected = EmptyMessageException.class)
    public void notBeEmpty() {
        String message = "";
        String userName = "dummy";

        TweetService tweetService = new TweetService(tweetsRepository, userService);
        tweetService.postTweet(userName, message);
    }

    @Test(expected = UserNotFoundException.class)
    public void notHaveInvalidUser() {
//        Setup
        String message = "you are dummy";
        String userName = "invalid_dummy";

        when(userService.getUser(userName)).thenThrow(new UserNotFoundException());
        TweetService tweetService = new TweetService(tweetsRepository, userService);

//        Action
        tweetService.postTweet(userName, message);

    }
    
    @Test
    public void replaceFoulWordWithDuck(){
//        Setup
        String message = "you are dummy";
        String userName = "invalid_dummy";

        TweetService tweetService = new TweetService(tweetsRepository, userService);

        tweetService.postTweet(userName,message);

        Tweet tweet = new Tweet();
        tweet.setMessage("you are duck");
        tweet.setUserName(userName);

        verify(tweetsRepository, times(1)).save(tweet);
    }

}
