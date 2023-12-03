package dev.waamir.hotelaluraapi.adapter.dto.resource.Booking;

import java.time.LocalDate;

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
    Long guestId,
    @NotNull
    Long roomId
) {
    public BookingResponse(Booking booking) {
        this(
            booking.getId(),
            booking.getCheckIn(),
            booking.getCheckOut(),
            booking.getTotalPrice(),
            booking.getGuest().getId(),
            booking.getRoom().getId()
        );
    }
}
