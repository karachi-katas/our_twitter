package com.crafting.our_twitter;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.exceptions.InvalidPasswordException;
import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.repository.model.User;
import com.crafting.our_twitter.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserShould {

    @Mock
    UsersRepository usersRepository;

    @Mock
    TweetsRepository tweetsRepository;

    @InjectMocks
    UserService userService;

    private static final Integer USER_ID = 6;
    public static final String USERNAME = "dummyUser";
    public static final String PASSWORD = "password";
    public static final String GENDER = "male";
    public static final String COUNTRY = "Uzbekistan";
    public static final String PHONE_NUMBER = "0334-1234567";

    @Test
    public void beAbleToSignUp() {

        OurUserCreationDTO ourUserCreationDTO = OurUserCreationDTO.builder()
                .userName(USERNAME)
                .password(PASSWORD)
                .gender(GENDER)
                .country(COUNTRY)
                .phonenumber(PHONE_NUMBER)
                .build();

        userService.createUser(ourUserCreationDTO);

        User user = getUser();
        user.setId(null);

        verify(usersRepository, times(1)).save(user);

    }

    private static User getUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setUserName(USERNAME);
        user.setPassword(PASSWORD);
        user.setGender(GENDER);
        return user;
    }

    @Test
    public void beAbleToSignIn() {

        User user = getUser();

        when(usersRepository.findByUserName(USERNAME)).thenReturn(Optional.of(user));

        // Action
        Integer userId = userService.signIn(USERNAME, PASSWORD);

        // Assertion
        Assert.assertEquals((Integer)6, userId);
        verify(usersRepository, times(1)).findByUserName(USERNAME);
    }

    @Test(expected = UserNotFoundException.class)
    public void notBeAbleToSignInWithInvalidUser() {

        when(usersRepository.findByUserName(USERNAME)).thenReturn(Optional.empty());

        // Action
        Integer userId = userService.signIn(USERNAME, PASSWORD);

        // Assertion

    }

    @Test(expected = InvalidPasswordException.class)
    public void notBeAbleToSignInWithInvalidPassword() {

        User user = getUser();

        when(usersRepository.findByUserName(USERNAME)).thenReturn(Optional.of(user));

        // Action
        String incorrectPassword = PASSWORD + "___";
        userService.signIn(USERNAME, incorrectPassword);

        // Assertion
    }

    @Test
    public void beAbleToPostATweet() {

        // SETUP
        String message = "message";
        User user = getUser();
        when(usersRepository.findByUserName(USERNAME)).thenReturn(Optional.of(user));

        // ACTION
        Integer tweetId =  userService.postTweet(USERNAME, message);

        // ASSERTION
        ArgumentCaptor<Tweet> tweetCaptor = ArgumentCaptor.forClass(Tweet.class);
        verify(tweetsRepository, times(1)).save(tweetCaptor.capture());
        verify(usersRepository, times(1)).findByUserName(USERNAME);

        Assert.assertEquals( tweetId,tweetCaptor.getValue().getId());
        Assert.assertEquals( message,tweetCaptor.getValue().getMessage());
    }
}
