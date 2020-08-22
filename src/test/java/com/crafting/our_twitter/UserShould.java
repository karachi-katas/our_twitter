package com.crafting.our_twitter;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.exceptions.InvalidPasswordException;
import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.User;
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

    @Test
    public void beAbleToSignUp() {

        UserService userService = new UserService(usersRepository);
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
        UserService userService = new UserService(usersRepository);

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
        UserService userService = new UserService(usersRepository);

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
        UserService userService = new UserService(usersRepository);

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
}
