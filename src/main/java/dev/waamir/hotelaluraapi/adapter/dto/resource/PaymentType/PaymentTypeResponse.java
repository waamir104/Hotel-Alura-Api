package dev.waamir.hotelaluraapi.adapter.dto.resource.PaymentType;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PaymentTypeResponse(
    @NotNull
    Long id,
    @NotEmpty
    String name
) {
    public PaymentTypeResponse(PaymentType paymentType) {
        this(paymentType.getId(), paymentType.getName());
    }
}
