package com.crafting.our_twitter.fixtures;

import com.crafting.our_twitter.repository.model.User;

public class UserFixture {

    public static final String USERNAME = "dummyUser";
    public static final String PASSWORD = "password";
    public static final String GENDER = "male";
    public static final String COUNTRY = "Uzbekistan";
    public static final String PHONE_NUMBER = "0334-1234567";
    private static final Integer USER_ID = 6;

    public static User getUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setUserName(USERNAME);
        user.setPassword(PASSWORD);
        user.setGender(GENDER);
        return user;
    }
}
