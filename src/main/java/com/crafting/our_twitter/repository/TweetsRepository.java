package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Tweets;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TweetsRepository extends JpaRepository<Tweets, Integer> {
}


