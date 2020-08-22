package com.crafting.our_twitter;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.exceptions.InvalidPasswordException;
import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.fixtures.UserFixture;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.User;
import com.crafting.our_twitter.service.UserManagementService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserShould {

    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    UserManagementService userManagementService;

    @Test
    public void beAbleToSignUp() {

        OurUserCreationDTO ourUserCreationDTO = OurUserCreationDTO.builder()
                .userName(UserFixture.USERNAME)
                .password(UserFixture.PASSWORD)
                .gender(UserFixture.GENDER)
                .country(UserFixture.COUNTRY)
                .phonenumber(UserFixture.PHONE_NUMBER)
                .build();

        userManagementService.createUser(ourUserCreationDTO);

        User user = UserFixture.getUser();
        user.setId(null);

        verify(usersRepository, times(1)).save(user);

    }

    @Test
    public void beAbleToSignIn() {

        User user = UserFixture.getUser();

        when(usersRepository.findByUserName(UserFixture.USERNAME)).thenReturn(Optional.of(user));

        // Action
        Integer userId = userManagementService.signIn(UserFixture.USERNAME, UserFixture.PASSWORD);

        // Assertion
        Assert.assertEquals((Integer)6, userId);
        verify(usersRepository, times(1)).findByUserName(UserFixture.USERNAME);
    }

    @Test(expected = UserNotFoundException.class)
    public void notBeAbleToSignInWithInvalidUser() {

        when(usersRepository.findByUserName(UserFixture.USERNAME)).thenReturn(Optional.empty());

        // Action
        Integer userId = userManagementService.signIn(UserFixture.USERNAME, UserFixture.PASSWORD);

        // Assertion

    }

    @Test(expected = InvalidPasswordException.class)
    public void notBeAbleToSignInWithInvalidPassword() {

        User user = UserFixture.getUser();

        when(usersRepository.findByUserName(UserFixture.USERNAME)).thenReturn(Optional.of(user));

        // Action
        String incorrectPassword = UserFixture.PASSWORD + "___";
        userManagementService.signIn(UserFixture.USERNAME, incorrectPassword);

        // Assertion
    }
}
