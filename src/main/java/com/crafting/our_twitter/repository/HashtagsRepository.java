package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HashtagsRepository extends JpaRepository<Hashtag, Integer> {
}


