package dev.waamir.hotelaluraapi.adapter.dto.resource.Room;

import dev.waamir.hotelaluraapi.domain.model.Room;
import jakarta.validation.constraints.NotEmpty;

public record RoomResponse(
    @NotEmpty Long number,
    @NotEmpty String description) {

    public RoomResponse(Room room) {
        this(room.getNumber(), room.getDescription());
    }
}
