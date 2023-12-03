package dev.waamir.hotelaluraapi.adapter.dto.resource.BookingPaymentType;

import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;
import jakarta.validation.constraints.NotNull;

public record BookingPaymentTypeResponse(
    @NotNull
    Long id,
    @NotNull
    double paymentPercentage,
    @NotNull
    Long paymentTypeId,
    @NotNull
    Long bookingId
) {
    public BookingPaymentTypeResponse(BookingPaymentType bookingPaymentType) {
        this(
            bookingPaymentType.getId(),
            bookingPaymentType.getPaymentPercentage(),
            bookingPaymentType.getPaymentType().getId(),
            bookingPaymentType.getBooking().getId()
        );
    }
}
