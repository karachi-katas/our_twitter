package com.crafting.our_twitter;

import com.crafting.our_twitter.Types.TweetReaction;
import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.dto.PostTweetDto;
import com.crafting.our_twitter.exceptions.InvalidPasswordException;
import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.repository.model.User;
import com.crafting.our_twitter.service.LikeService;
import com.crafting.our_twitter.service.TweetService;
import com.crafting.our_twitter.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserShould {

    @Mock
    UsersRepository usersRepository;

    @Mock
    TweetService tweetService;

    @Mock
    LikeService likeService;

    @Test
    public void beAbleToSignUp() {

        UserService userService = new UserService(usersRepository, null, null);
        String username = "dummyUser";
        String password = "password";
        String gender = "male";
        String country = "Uzbekistan";
        String phoneNumber = "0334-1234567";

        OurUserCreationDTO ourUserCreationDTO = OurUserCreationDTO.builder()
                .userName(username)
                .password(password)
                .gender(gender)
                .country(country)
                .phonenumber(phoneNumber)
                .build();

        userService.createUser(ourUserCreationDTO);

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setGender(gender);

        verify(usersRepository, times(1)).save(user);

    }

    @Test
    public void beAbleToSignIn() {

        // Setup
        UserService userService = new UserService(usersRepository, null, null);

        String userName = "dummyUser";
        String password = "password";

        User user = new User();
        user.setId(6);
        user.setPassword(password);

        when(usersRepository.findByUserName(userName)).thenReturn(Optional.of(user));

        // Action
        Integer userId = userService.signIn(userName, password);

        // Assertion
        Assert.assertEquals((Integer)6, userId);
        verify(usersRepository, times(1)).findByUserName(userName);
    }

    @Test(expected = UserNotFoundException.class)
    public void notBeAbleToSignInWithInvalidUser() {

        // Setup
        UserService userService = new UserService(usersRepository, null, null);

        String userName = "dummyUser";
        String password = "password";

        when(usersRepository.findByUserName(userName)).thenReturn(Optional.empty());

        // Action
        Integer userId = userService.signIn(userName, password);

        // Assertion

    }

    @Test(expected = InvalidPasswordException.class)
    public void notBeAbleToSignInWithInvalidPassword() {

        // Setup
        UserService userService = new UserService(usersRepository, null, null);

        String userName = "dummyUser";
        String password = "password";

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);

        when(usersRepository.findByUserName(userName)).thenReturn(Optional.of(user));

        // Action
        String incorrectPassword = password + "___";
        userService.signIn(userName, incorrectPassword);

        // Assertion

    }



    @Test
    public void ableToPostATweet(){

        // Setup

        UserService userService = new UserService(usersRepository, tweetService, null);

        Integer userId = 5;
        String userName = "john";
        User user = new User();
        user.setUserName(userName);
        String tweetMessage = "TestMessage";
        Tweet tweet = new Tweet();
        tweet.setUserName(userName);
        tweet.setMessage(tweetMessage);


        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));


        // Action

        PostTweetDto postTweetDto = new PostTweetDto(userId,tweetMessage);


        userService.postTweet(postTweetDto);

        // Assertion

        verify(usersRepository, times(1)).findById(userId);
        verify(tweetService,times(1)).post(tweet);




    }



    @Test(expected = UserNotFoundException.class)
    public void notBeAbleToPostTweetIfInvalidUser(){

        // Setup

        UserService userService = new UserService(usersRepository, tweetService, null);

        Integer userId = 5;
        String userName = "john";
        User user = new User();
        user.setUserName(userName);
        String tweetMessage = "TestMessage";
        Tweet tweet = new Tweet();
        tweet.setUserName(userName);
        tweet.setMessage(tweetMessage);


        when(usersRepository.findById(userId)).thenReturn(Optional.empty());


        // Action

        PostTweetDto postTweetDto = new PostTweetDto(userId,tweetMessage);


        userService.postTweet(postTweetDto);

        // Assertion

        verify(usersRepository, times(1)).findById(userId);
        verify(tweetService,times(0)).post(tweet);




    }


    @Test
    public void beAbleToLikeATweet() {

        //arrange
        UserService userService = new UserService(usersRepository, null, likeService);

        Integer userId = 5;
        Integer tweetId = 10;

        //act
        userService.likeTweet(userId, tweetId);

        //assert
        verify(likeService, times(1)).react(userId, tweetId, TweetReaction.Like);
    }

    @Test
    public void beAbleToDisLikeATweet() {

        //arrange
        UserService userService = new UserService(usersRepository, null, likeService);

        Integer userId = 5;
        Integer tweetId = 10;

        //act
        userService.disLikeTweet(userId, tweetId);

        //assert
        verify(likeService, times(1)).react(userId, tweetId, TweetReaction.Dislike);
    }



}
