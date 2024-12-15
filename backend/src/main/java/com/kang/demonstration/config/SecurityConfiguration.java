package com.kang.demonstration.config;

import com.kang.demonstration.auth.unit.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author kanghouchao
 */
@Configuration
public class SecurityConfiguration {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public SecurityConfiguration(TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] allowedPaths = {"/register", "/login", "/error", "/create-user"};
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/**")
            ).authorizeHttpRequests(authorize -> authorize
                .requestMatchers(allowedPaths).permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(tokenAuthenticationFilter, LoginAuthenticationFilter.class)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        return http.build();
    }

    @Bean
    public LoginAuthenticationFilter jwtAuthenticationFilter(TokenProvider tokenProvider, HttpSecurity http) throws Exception {
        return new LoginAuthenticationFilter(new AntPathRequestMatcher("/login", "POST"), tokenProvider, authenticationManager(http));
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

}
