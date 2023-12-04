package dev.waamir.hotelaluraapi.adapter.dto.resource.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(
    @NotEmpty
    String username,
    @NotEmpty
    String password,
    @NotNull
    Long roleId
) {
    
}
