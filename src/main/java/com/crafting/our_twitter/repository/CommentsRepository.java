package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentsRepository extends JpaRepository<Comments, Integer> {
}


