package dev.waamir.hotelaluraapi.adapter.dto.resource.Booking;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record BookingUpdateRequest (
    @NotNull
    long id,
    @NotNull
    LocalDate checkIn,
    @NotNull
    LocalDate checkOut
) {}
