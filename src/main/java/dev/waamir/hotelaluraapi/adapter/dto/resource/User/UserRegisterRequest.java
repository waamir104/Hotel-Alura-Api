package dev.waamir.hotelaluraapi.adapter.dto.resource.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(
    @NotEmpty
    String username,
    @NotEmpty
    String password1,
    @NotEmpty
    String password2,
    @NotNull
    Long roleId
) {
    
}
