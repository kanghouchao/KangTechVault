package com.kang.demonstration.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kanghouchao
 */
@SpringBootTest()
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Test
    void testRegister() {
        final String request = "{\"email\": \"kanhouchou@gmail.com\"}";
        assertThat(this.mockMvcTester.post()
            .uri("/register/email")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .hasStatusOk()
            .hasBodyTextEqualTo("");
    }
}
