package com.miraie.springboot.webapp.scheduler.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;
import java.util.function.Function;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager condigInMemoryUserDetails () {

        UserDetails newUser1 = createNewUser("milo", "kukkut");
        UserDetails newUser2 = createNewUser("pokoyo", "thokoyo");
        UserDetails newUser3 = createNewUser("guggul", "punav");
        return new InMemoryUserDetailsManager(newUser1, newUser2, newUser3);
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
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    /*
        @TODO: Not clear
        This is added as h2 db console is not accessed without enabling these settings.
        By default the Spring security does following things
        1. All URLs are protected.
        2. login page when unauthorized access is attempted

        To enable the H2 pages we need to disable
        1. CSRF : Cross Side Request Forgery
        2. Enable frames.

        This is achieved by setting up filter chain which will override the default request
        filters
     */
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        // All request need to be authorized
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        // show login form
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.csrf(Customizer.withDefaults());
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.build();

    }
}
