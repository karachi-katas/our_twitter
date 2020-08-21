package com.crafting.our_twitter.repository;

import com.crafting.our_twitter.repository.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentsRepository extends JpaRepository<Comment, Integer> {
}


