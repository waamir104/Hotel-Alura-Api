package dev.waamir.hotelaluraapi.adapter.dto.resource.BookingPaymentType;

import jakarta.validation.constraints.NotNull;

public record BookingPaymentTypeUpdateRequest(
    @NotNull
    Long id,
    @NotNull
    double paymentPercentage,
    @NotNull
    Long paymentTypeId
) {
    
}
