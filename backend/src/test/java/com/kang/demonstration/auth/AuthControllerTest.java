package com.kang.demonstration.auth;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kang.demonstration.auth.entity.User;
import com.kang.demonstration.auth.repository.UserRepository;
import com.kang.demonstration.auth.unit.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"password\", \"passwordConfirm\": \"password\", \"email\": \"user@example.com\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string("OK"));
    }

    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("password"));
        when(this.userRepository.getUserByEmail(user.getEmail())).thenReturn(user);
        when(this.jwtUtil.generateToken(Collections.singletonMap("email", user.getEmail()), 1000 * 60 * 60)).thenReturn("mock-token");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"user@example.com\", \"password\": \"password\"}"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"token\": \"mock-token\"}"));
    }


}