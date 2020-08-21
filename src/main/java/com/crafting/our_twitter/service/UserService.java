package com.crafting.our_twitter.service;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.User;
import org.springframework.stereotype.Service;

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
}
