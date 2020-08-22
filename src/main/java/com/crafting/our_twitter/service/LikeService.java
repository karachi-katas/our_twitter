package com.crafting.our_twitter.service;

import com.crafting.our_twitter.Types.TweetReaction;
import com.crafting.our_twitter.repository.LikesRepository;
import com.crafting.our_twitter.repository.model.Like;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

  LikesRepository likesRepository;

  public LikeService(LikesRepository likesRepository) {
    this.likesRepository = likesRepository;
  }

  public void react(Integer userId, Integer tweetId,
      TweetReaction tweetReaction) {
    Like like = new Like();
    like.setUserId(userId);
    like.setTweetId(tweetId);
    like.setReactionEnum(tweetReaction.ordinal());

    likesRepository.save(like);
  }
}
