package com.crafting.our_twitter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.crafting.our_twitter.Types.TweetReaction;
import com.crafting.our_twitter.repository.LikesRepository;
import com.crafting.our_twitter.repository.model.Like;
import com.crafting.our_twitter.service.LikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LikeServiceShould {

  @Mock
  LikesRepository likesRepository;

  @Test
  public void beAbleToLike(){
    //arrange
    LikeService likeService = new LikeService(likesRepository);


    Integer userId = 5;
    Integer tweetId = 10;

    Like like = new Like();
    like.setTweetId(tweetId);
    like.setUserId(userId);
    like.setReactionEnum(0);

    //act
    likeService.react(userId, tweetId, TweetReaction.Like);

    //assert
    verify(likesRepository, times(1)).save(like);
  }

  @Test
  public void beAbleToDisLike(){
    //arrange
    LikeService likeService = new LikeService(likesRepository);


    Integer userId = 5;
    Integer tweetId = 10;

    Like like = new Like();
    like.setTweetId(tweetId);
    like.setUserId(userId);
    like.setReactionEnum(1);

    //act
    likeService.react(userId, tweetId, TweetReaction.Dislike);

    //assert
    verify(likesRepository, times(1)).save(like);
  }
}
