package dev.waamir.hotelaluraapi.adapter.dto.resource.Room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateRoomRequest (
	@NotNull(message = "Room number cannot be empty")
    @Positive
	Long number,
    @NotEmpty
	String description,
	@NotEmpty
	String roomTypeName
) {
    
}
