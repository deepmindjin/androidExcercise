package com.hongseokandrewjang.android.firebase_auth;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HongSeokAndrewJang on 2016-10-31.
 */

public class User {
    String userId;
    String userPassword;
    String userEmail;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public User(String userEmail, String userPassword) {
        userId = UUID.randomUUID().toString();
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userPassword", userPassword);
        result.put("userEmail", userEmail);
        return result;
    }
}
