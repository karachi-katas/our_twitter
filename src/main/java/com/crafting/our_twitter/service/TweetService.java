package com.crafting.our_twitter.service;

import com.crafting.our_twitter.repository.TweetsRepository;
import com.crafting.our_twitter.repository.model.Tweet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TweetService {

    private final UserManagementService userManagementService;
    private final TweetsRepository tweetsRepository;

    public Integer postTweet(String userName, String message) {

        userManagementService.getUser(userName);

        Tweet newTweet =  new Tweet(5, message);
        tweetsRepository.save(newTweet);

        return newTweet.getId();
    }
}
