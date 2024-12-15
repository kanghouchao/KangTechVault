package com.kang.demonstration.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author kanghouchao
 */
public record PasswordSettingRequest(@Email @NotBlank String email,
                                     @NotBlank String token,
                                     @NotBlank String password,
                                     @NotBlank String passwordConfirm) {
}
