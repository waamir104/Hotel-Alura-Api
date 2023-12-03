package dev.waamir.hotelaluraapi.adapter.dto.resource.PaymentType;

import jakarta.validation.constraints.NotEmpty;

public record PaymentTypeRequest(
    @NotEmpty
    String name
) {
}
