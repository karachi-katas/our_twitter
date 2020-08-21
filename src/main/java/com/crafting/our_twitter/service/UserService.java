package com.crafting.our_twitter.service;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.exceptions.InvalidPasswordException;
import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void createUser(OurUserCreationDTO ourUserCreationDTO) {

        User user = new User();
        user.setUserName(ourUserCreationDTO.getUserName());
        user.setPassword(ourUserCreationDTO.getPassword());
        user.setGender(ourUserCreationDTO.getGender());
        usersRepository.save(user);
    }

    public int signIn(String userName, String password) {
        Optional<User> userOptional = usersRepository.findByUserName(userName);

        if (!userOptional.isPresent()) throw new UserNotFoundException();

        User user = userOptional.get();
        user.validatePassword(password);

        return user.getId();
    }
}
