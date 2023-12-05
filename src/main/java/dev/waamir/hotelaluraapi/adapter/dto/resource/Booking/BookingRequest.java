package dev.waamir.hotelaluraapi.adapter.dto.resource.Booking;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record BookingRequest (
    @NotNull
    LocalDate checkIn,
    @NotNull
    LocalDate checkOut,
    @NotNull
    String guestEmail,
    @NotNull
    long roomNumber
) {
}
