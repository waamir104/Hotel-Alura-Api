package dev.waamir.hotelaluraapi.adapter.dto.resource.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserUpdateRequest (
    @NotNull
    Long id,
    @NotEmpty
    String username,
    @NotNull
    Long roleId
) {
    
}
