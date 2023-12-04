package dev.waamir.hotelaluraapi.adapter.dto.resource.User;

import dev.waamir.hotelaluraapi.domain.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserResponse (
    @NotNull
    Long id,
    @NotEmpty
    @Email
    String username,
    @NotNull
    Long roleId
) {
    public UserResponse (User user) {
        this(user.getId(), user.getUsername(), user.getRole().getId());
    }
}
