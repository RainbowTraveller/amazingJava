package com.miraie.springboot.webapp.scheduler.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String name, String password) {
        boolean isValidUsername = name.equalsIgnoreCase("milo");
        boolean isValidPassword = password.equalsIgnoreCase("kukkut");

        return isValidUsername && isValidPassword;
    }
}
