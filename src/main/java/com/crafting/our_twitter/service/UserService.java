package com.crafting.our_twitter.service;

import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void createUser(OurUserCreationDTO ourUserCreationDTO) {

        Users users = new Users();
        users.setUserName(ourUserCreationDTO.getUserName());
        users.setPassword(ourUserCreationDTO.getPassword());
        users.setGender(ourUserCreationDTO.getGender());
        usersRepository.save(users);
    }
}
