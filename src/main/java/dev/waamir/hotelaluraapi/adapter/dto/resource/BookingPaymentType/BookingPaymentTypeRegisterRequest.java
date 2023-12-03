package dev.waamir.hotelaluraapi.adapter.dto.resource.BookingPaymentType;

import jakarta.validation.constraints.NotNull;

public record BookingPaymentTypeRegisterRequest(
    @NotNull
    double paymentPercentage,
    @NotNull
    Long paymentTypeId,
    @NotNull
    Long bookingId
) {
}
