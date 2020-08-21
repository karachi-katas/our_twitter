package com.crafting.our_twitter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.User;
import com.crafting.our_twitter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceShould {

    @Mock
    UsersRepository usersRepository;

    @Test
    public void createUser() {

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
}
