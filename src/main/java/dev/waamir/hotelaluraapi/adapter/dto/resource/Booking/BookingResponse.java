package dev.waamir.hotelaluraapi.adapter.dto.resource.Booking;

import java.time.LocalDate;

import dev.waamir.hotelaluraapi.adapter.dto.resource.Guest.GuestDto;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Room.RoomDto;
import dev.waamir.hotelaluraapi.domain.model.Booking;
import jakarta.validation.constraints.NotNull;

public record BookingResponse (
    @NotNull
    long id,
    @NotNull
    LocalDate checkIn,
    @NotNull
    LocalDate checkOut,
    Double totalPrice,
    @NotNull
    GuestDto guestDto,
    @NotNull
    RoomDto roomDto
) {
    public BookingResponse(Booking booking) {
        this(
            booking.getId(),
            booking.getCheckIn(),
            booking.getCheckOut(),
            booking.getTotalPrice(),
            new GuestDto(booking.getGuest()),
            new RoomDto(booking.getRoom())
        );
    }
}
