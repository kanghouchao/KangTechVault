package com.kang.demonstration.auth.controller;

import com.kang.demonstration.auth.model.EmailSenderRequest;
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
@RequestMapping("register")
public class RegisterController {

    private final RegisterService registerService;

    /**
     * Send Email for new user
     *
     * @param emailSenderRequest user's email
     * @return response
     */
    @PostMapping("send-email")
    public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailSenderRequest emailSenderRequest) {
        try {
            this.registerService.sendEmail(emailSenderRequest.email());
        } catch (MessagingException e) {
            //TODO should do more better
            log.error(e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

}
