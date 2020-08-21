package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<User, Integer> {
}


