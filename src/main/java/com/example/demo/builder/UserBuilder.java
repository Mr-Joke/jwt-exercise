package com.example.demo.builder;

import com.example.demo.domain.User;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 10:01
 **/
public class UserBuilder {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder username(String username){
        user.setUsername(username);
        return this;
    }

    public UserBuilder password(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder email(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder validate(Boolean validate){
        user.setValidate(validate);
        return this;
    }

    public UserBuilder enable(Boolean enable){
        user.setEnable(enable);
        return this;
    }

    public User build(){
        return user;
    }
}
