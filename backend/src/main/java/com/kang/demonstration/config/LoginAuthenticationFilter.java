package com.kang.demonstration.config;

import com.kang.demonstration.auth.unit.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;
import java.util.Objects;

/**
 * @author kanghouchao
 */
@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenProvider tokenProvider;

    public LoginAuthenticationFilter(AntPathRequestMatcher antPathRequestMatcher, TokenProvider tokenProvider,
                                     AuthenticationManager authenticationManager) {
        super(authenticationManager);
        super.setRequiresAuthenticationRequestMatcher(antPathRequestMatcher);
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)
            || authentication instanceof AnonymousAuthenticationToken
            || !authentication.isAuthenticated()) {
            return super.attemptAuthentication(request, response);
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token;
        if (authentication instanceof JwtAuthenticationToken) {
            token = (String) authentication.getCredentials();
        } else {
            token = tokenProvider.generateToken(authResult.getName(), Collections.emptyList());
        }
        ResponseCookie cookie = ResponseCookie.from("token", token)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(tokenProvider.getExpirationTimeMillis())
            .build();
        response.addHeader("Set-Cookie", cookie.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
