package com.crafting.our_twitter.service;

import com.crafting.our_twitter.Types.TweetReaction;
import com.crafting.our_twitter.dto.OurUserCreationDTO;
import com.crafting.our_twitter.dto.PostTweetDto;
import com.crafting.our_twitter.exceptions.UserNotFoundException;
import com.crafting.our_twitter.repository.UsersRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import com.crafting.our_twitter.repository.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    private final TweetService tweetService;
    private final LikeService likeService;

    public UserService(UsersRepository usersRepository, TweetService tweetService,
        LikeService likeService) {
        this.usersRepository = usersRepository;
        this.tweetService = tweetService;
        this.likeService = likeService;
    }

    public void createUser(OurUserCreationDTO ourUserCreationDTO) {

        User user = new User();
        user.setUserName(ourUserCreationDTO.getUserName());
        user.setPassword(ourUserCreationDTO.getPassword());
        user.setGender(ourUserCreationDTO.getGender());
        usersRepository.save(user);
    }

    public Integer signIn(String userName, String password) {

        User user = getUser(userName);
        user.guardAgainstInvalidPassword(password);
        return user.getId();
    }

    private User getUser(String userName) {
        Optional<User> userOptional = usersRepository.findByUserName(userName);
        guardAgainstMissingUser(userOptional);

        return userOptional.get();
    }

    private void guardAgainstMissingUser(Optional<User> userOptional) {
        if (!userOptional.isPresent()) throw new UserNotFoundException();
    }

    public void postTweet(PostTweetDto postTweetDto) {

        Optional<User> user = usersRepository.findById(postTweetDto.getUserId());
        guardAgainstMissingUser(user);
        Tweet tweet = Tweet.createFor(user.get().getUserName(),postTweetDto.getMessage());
        tweetService.post(tweet);
    }

    public void likeTweet(Integer userId, Integer tweetId) {
        likeService.react(userId, tweetId, TweetReaction.Like);
    }

    public void disLikeTweet(Integer userId, Integer tweetId) {
        likeService.react(userId, tweetId, TweetReaction.Dislike);
    }
}
