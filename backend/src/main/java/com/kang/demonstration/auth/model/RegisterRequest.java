package com.kang.demonstration.auth.model;

import com.kang.demonstration.auth.model.validation.Existed;
import com.kang.demonstration.auth.model.validation.SamePassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author kanghouchao
 */
@SamePassword
public record RegisterRequest(@NotBlank String password,
                              @NotBlank String passwordConfirm,
                              @Email @Existed String email) {
}
