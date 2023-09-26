package com.miraie.springboot.webapp.scheduler.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager condigInMemoryUserDetails () {

        UserDetails newUser1 = createNewUser("milo", "kukkut");
        UserDetails newUser2 = createNewUser("pokoyo", "thokoyo");
        UserDetails newUser3 = createNewUser("guggul", "punav");
        return new InMemoryUserDetailsManager(newUser1,newUser2, newUser3);
    }

    private UserDetails createNewUser (String username, String password) {
        Function<String, String> passwordEncoder =
                input -> passwordEncoder().encode(input);
        // This was initial implementation, but the method is deprecated
        // UserDetails userDetails = User.withDefaultPasswordEncoder(passwordEncoder)
        // Added new encoder to avoid the above method
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
