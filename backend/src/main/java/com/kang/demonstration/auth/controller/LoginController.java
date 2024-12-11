package com.kang.demonstration.auth.controller;

import com.kang.demonstration.auth.model.LoginRequest;
import com.kang.demonstration.auth.model.LoginResponse;
import com.kang.demonstration.auth.unit.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author kanghouchao
 */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginController {

    @Value(value = "${auth.expiration.time-millis}")
    private Long expirationTimeMillis = 1000 * 60 * 60 * 24L;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@Valid LoginRequest request) {
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        final String token = jwtUtil.generateToken(
            Collections.singletonMap("email", request.email()), expirationTimeMillis);
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
