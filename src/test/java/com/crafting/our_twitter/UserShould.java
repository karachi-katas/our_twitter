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

        com.crafting.our_twitter.repository.model.User user = new com.crafting.our_twitter.repository.model.User();
        user.setUserName(username);
        user.setPassword(password);
        user.setGender(gender);

        verify(usersRepository, times(1)).save(user);

    }

    @Test
    public void beAbleToSignIn() {
        UserService userService = new UserService(usersRepository);

        String userName = "dummyUser";
        String password = "password";

        User dummyUser = new User();

        dummyUser.setId(3);
        dummyUser.setUserName(userName);
        dummyUser.setPassword(password);
        dummyUser.setGender("Male");

        when(usersRepository.findByUserName(userName)).thenReturn(Optional.of(dummyUser));

        int id = userService.signIn(userName, password);

        verify(usersRepository, times(1)).findByUserName(userName);
        Assert.assertEquals(id, 3);
    }

    @Test(expected = UserNotFoundException.class)
    public void notBeAbleToSignInWithInvalidUser() {
        UserService userService = new UserService(usersRepository);

        String userName = "dummyUser";
        String password = "password";

        when(usersRepository.findByUserName(userName)).thenReturn(Optional.empty());

        int id = userService.signIn(userName, password);
    }

    @Test(expected = InvalidPasswordException.class)
    public void notBeAbleToSignInWithInvalidPassword() {
        UserService userService = new UserService(usersRepository);

        String userName = "dummyUser";
        String password = "password";

        User dummyUser = new User();

        dummyUser.setId(3);
        dummyUser.setUserName(userName);
        dummyUser.setPassword(password);
        dummyUser.setGender("Male");

        String testPassword = password + "_wrong";

        when(usersRepository.findByUserName(userName)).thenReturn(Optional.of(dummyUser));

        int id = userService.signIn(userName, testPassword);

        verify(usersRepository, times(1)).findByUserName(userName);
        Assert.assertEquals(id, 3);
    }

}
