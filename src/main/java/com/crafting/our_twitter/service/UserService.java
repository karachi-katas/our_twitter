package com.crafting.our_twitter.service;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
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

    public Integer signIn(String userName, String password) {

        User user = getUser(userName);
        user.guardAgainstInvalidPassword(password);
        return user.getId();
    }

    public User getUser(String userName) {
        Optional<User> userOptional = usersRepository.findByUserName(userName);
        guardAgainstMissingUser(userOptional);

        return userOptional.get();
    }

    private void guardAgainstMissingUser(Optional<User> userOptional) {
        if (!userOptional.isPresent()) throw new UserNotFoundException();
    }
}
