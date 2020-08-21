package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikesRepository extends JpaRepository<Like, Integer> {
}


