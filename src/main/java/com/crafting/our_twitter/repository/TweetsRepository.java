package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TweetsRepository extends JpaRepository<Tweet, Integer> {
}


