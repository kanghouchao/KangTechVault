package com.kang.demonstration.auth.controller;

import com.kang.demonstration.auth.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author kanghouchao
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegisterControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    public RegisterService registerService;

    @Test
    void testSendEmail() throws Exception {
        final String request = "{\"email\": \"kanhouchou@gmail.com\"}";

        assertThat(this.mockMvcTester.post()
            .uri("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request)
        ).hasStatusOk().hasBodyTextEqualTo("");

        verify(registerService, times(1))
            .sendEmail(eq("kanhouchou@gmail.com"));
    }

    @Test
    void testSendEmailWithoutEmail() throws Exception {
        final String request = "{\"email\": \"\"}";

        assertThat(this.mockMvcTester.post()
            .uri("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request)
        ).hasStatus(HttpStatus.BAD_REQUEST)
            .hasBodyTextEqualTo("{\"message\":null,\"validationErrors\":[{\"field\":\"email\",\"message\":\"空白は許可されていません\"}]}");

        verify(registerService, times(0))
            .sendEmail(any());
    }

    @Test
    void testSendEmailWithInvalidEmail() throws Exception {
        final String request = "{\"email\": \"this is not a valid email\"}";

       assertThat(this.mockMvcTester.post()
            .uri("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request)
        ).hasStatus(HttpStatus.BAD_REQUEST)
            .hasBodyTextEqualTo("{\"message\":null,\"validationErrors\":[{\"field\":\"email\",\"message\":\"電子メールアドレスとして正しい形式にしてください\"}]}");

        verify(registerService, times(0))
            .sendEmail(any());
    }

}
