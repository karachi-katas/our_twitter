package com.crafting.our_twitter.service;

import java.util.List;

public class SwearWordsService {

    public List<String> getSwearWords() {
        return null;
    }

    public String replaceSwearWords(String message) {
        List<String> swearWords = getSwearWords();

        for (String swearWord : swearWords) {
            message = message.replace(swearWord, "duck");
        }
        return message;
    }
}
