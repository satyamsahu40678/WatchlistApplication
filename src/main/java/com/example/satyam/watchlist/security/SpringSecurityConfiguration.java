package com.example.satyam.watchlist.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        1) All reqeusts should be authenticated
        http.authorizeHttpRequests(
                auth -> auth.requestMatchers("/").permitAll().anyRequest().authenticated()
        );
//        2) If a request is not authenticated, a web page is shown
        http.httpBasic(Customizer.withDefaults());
//        3) CSRF -> Post,Put
        http.csrf().disable();

        return http.build();
    }
}

