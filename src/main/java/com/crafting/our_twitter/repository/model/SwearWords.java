package com.crafting.our_twitter.repository.model;

import java.util.Arrays;
import java.util.List;

public class SwearWords {
    private final List<String> SWEAR_WORDS = Arrays.asList("stupid", "dog", "mad");

    public String censor(String message) {
        for (String word: SWEAR_WORDS) {
            message = message.replace(word, "duck");
        }
        return message;
    }
}
