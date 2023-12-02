package dev.waamir.hotelaluraapi.adapter.dto.resource.PaymentType;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;
import jakarta.validation.constraints.NotEmpty;

public record PaymentTypeDto(
    @NotEmpty
    String name
) {
    public PaymentTypeDto(PaymentType paymentType) {
        this(paymentType.getName());
    }
}
