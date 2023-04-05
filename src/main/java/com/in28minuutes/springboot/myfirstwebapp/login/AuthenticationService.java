package com.in28minuutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password){
        boolean isValidUser=username.equalsIgnoreCase("rupesh");
        boolean isValidPassword=password.equals("bhosdu");

        return isValidPassword&&isValidUser;
    }
}
