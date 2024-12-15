package com.kang.demonstration.auth.controller;

import com.kang.demonstration.auth.entity.User;
import com.kang.demonstration.auth.model.EmailSenderRequest;
import com.kang.demonstration.auth.model.PasswordSettingRequest;
import com.kang.demonstration.auth.service.RegisterService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kanghouchao
 */

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping
public class RegisterController {

    private final RegisterService registerService;

    /**
     * Send Email for new user
     *
     * @param emailSenderRequest user's email
     * @return response
     */
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid EmailSenderRequest emailSenderRequest) throws MessagingException {
        this.registerService.sendEmail(emailSenderRequest.email());
        return ResponseEntity.ok().build();
    }

    @PostMapping("create-user")
    public ResponseEntity<Long> createNewUser(@Valid @RequestBody PasswordSettingRequest request) {
        final User user = this.registerService.createNewUser(request.email(), request.token(), request.password());
        return ResponseEntity.ok(user.getId());
    }

}
