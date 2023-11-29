package dev.waamir.hotelaluraapi.adapter.dto.resource.Room;

import dev.waamir.hotelaluraapi.domain.model.Room;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RoomDto(
    @NotNull @Positive Long number,
    @NotEmpty String description) {
        
    public RoomDto(Room room) {
        this(room.getNumber(), room.getDescription());
    }
}
