package dev.waamir.hotelaluraapi.adapter.dto.resource.RoomType;

import dev.waamir.hotelaluraapi.domain.model.RoomType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomTypeResponse (
    @NotNull
    Long id,
    @NotEmpty
    String name,
    @NotEmpty
    String description,
    @NotNull
    double dailyPrice
) {
    public RoomTypeResponse (RoomType roomType) {
        this(roomType.getId(), roomType.getName(), roomType.getDescription(), roomType.getDailyPrice());
    }
}
