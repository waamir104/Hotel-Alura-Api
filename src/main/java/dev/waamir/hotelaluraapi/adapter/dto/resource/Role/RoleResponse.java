package dev.waamir.hotelaluraapi.adapter.dto.resource.Role;

import dev.waamir.hotelaluraapi.domain.model.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoleResponse(
    @NotNull
    Long id,
    @NotEmpty
    String name
) {
    public RoleResponse(Role role) {
        this(role.getId(), role.getName());
    }
}
