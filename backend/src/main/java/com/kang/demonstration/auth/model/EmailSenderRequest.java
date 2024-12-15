package com.kang.demonstration.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author kanghouchao
 */
public record EmailSenderRequest(@NotBlank @Email String email) {

}
