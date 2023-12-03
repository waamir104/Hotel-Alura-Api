package dev.waamir.hotelaluraapi.adapter.dto.resource.RoomType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomTypeRegisterRequest(
    @NotEmpty
    String name,
    @NotEmpty
    String description,
    @NotNull
    double dailyPrice
) {
}
