package com.kang.demonstration.auth.model;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author kanghouchao
 */
public record AuthRequest(@NotEmpty String username, @NotEmpty String password) {

}
