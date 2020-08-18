package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Hashtags;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HashtagsRepository extends JpaRepository<Hashtags, Integer> {
}


