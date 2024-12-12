package com.kang.demonstration.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.kang.demonstration.auth.entity.User;
import com.kang.demonstration.auth.unit.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LoginControllerTest {

    @Value(value = "${auth.expiration.time-millis}")
    private Long expirationTimeMillis;

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    public JwtUtil jwtUtil;

    @MockitoBean
    public UserDetailsService userDetailsService;

    // Login TEST

    @Test
    void testLogin() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("password"));
        when(this.userDetailsService.loadUserByUsername(user.getUsername()))
            .thenReturn(user);
        when(this.jwtUtil.generateToken(Collections.singletonMap("email", user.getEmail()), this.expirationTimeMillis))
            .thenReturn("mock-token");

        assertThat(this.mockMvcTester
            .post().uri("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\": \"user@example.com\", \"password\": \"password\"}"))
            .hasStatusOk()
            .hasBodyTextEqualTo("{\"token\": \"mock-token\"}");
    }


}