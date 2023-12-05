package dev.waamir.hotelaluraapi.adapter.dto.resource.Auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthResetPwdRequest(
    @NotEmpty
    String username,
    @NotEmpty
    String password1,
    @NotEmpty
    String password2,
    @NotEmpty
    String token
) {
    
}
