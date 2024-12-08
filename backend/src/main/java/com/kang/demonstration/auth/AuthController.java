package com.kang.demonstration.auth;

import com.kang.demonstration.auth.entity.User;
import com.kang.demonstration.auth.model.LoginRequest;
import com.kang.demonstration.auth.model.LoginResponse;
import com.kang.demonstration.auth.model.RegisterRequest;
import com.kang.demonstration.auth.service.AccountService;
import com.kang.demonstration.auth.unit.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author kanghouchao
 */
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    /**
     * user login
     *
     * @param request email and password
     * @return jwt token
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        log.trace("Login for user: {}", request.email());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        final String token = jwtUtil.generateToken(Collections.singletonMap("email", request.email()), 1000 * 60 * 60);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    /**
     * user register
     *
     * @param request email and password twice
     * @return "OK"
     */
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        final User user = new User();
        user.setEmail(request.email());
        user.setPassword(this.passwordEncoder.encode(request.password()));
        log.trace("User(Id: {}) has saved.", this.accountService.save(user));
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
