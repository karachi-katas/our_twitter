package com.crafting.our_twitter.service;


import com.crafting.our_twitter.exceptions.EmptyMessageException;
import com.crafting.our_twitter.repository.LikesRepository;
import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Like;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.repository.model.User;
import org.springframework.stereotype.Service;

@Service
public class TweetService
{
    private final TweetsRepository tweetsRepository;
    private final LikesRepository likesRepository;

    private final UserService userService;

    public TweetService(TweetsRepository tweetsRepository, LikesRepository likesRepository, UserService userService) {
        this.tweetsRepository = tweetsRepository;
        this.likesRepository = likesRepository;
        this.userService = userService;
    }

    public void postTweet(String userName,String message)
    {

        if(message.isEmpty())  throw new EmptyMessageException();
        message=message.replaceAll("dummy", "duck");
        User user = userService.getUser(userName);

        Tweet tweet = new Tweet();
        tweet.setUserName(user.getUserName());
        tweet.setMessage(message);

        tweetsRepository.save(tweet);

    }


    public void reactOnTweet(Integer reaction,String userName,Integer tweetId)
    {
        User user = userService.getUser(userName);

        Like like =new Like();
        like.setUserId(user.getId());
        like.setReactionEnum(reaction);
        like.setTweetId(tweetId);


        likesRepository.save(like);

    }
}
