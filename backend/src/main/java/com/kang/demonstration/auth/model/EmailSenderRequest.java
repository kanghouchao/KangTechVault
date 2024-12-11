package com.kang.demonstration.auth.model;

import com.kang.demonstration.auth.model.validation.Existed;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Locale;

/**
 * @author kanghouchao
 */
public record EmailSenderRequest(@NotBlank @Email @Existed String email,
                                 Locale locale) {

}
