package dev.waamir.hotelaluraapi.adapter.dto.resource.Booking;

import java.time.LocalDate;

import dev.waamir.hotelaluraapi.adapter.dto.resource.Guest.GuestDto;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Room.RoomDto;
import jakarta.validation.constraints.NotNull;

public record BookingUpdateRequest (
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
) {}
