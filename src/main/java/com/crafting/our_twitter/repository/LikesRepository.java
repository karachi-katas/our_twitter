package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikesRepository extends JpaRepository<Likes, Integer> {
}


