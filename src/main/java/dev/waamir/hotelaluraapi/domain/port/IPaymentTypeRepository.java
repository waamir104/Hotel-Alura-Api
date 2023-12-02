package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;

import java.util.List;
import java.util.Optional;

public interface IPaymentTypeRepository<T extends PaymentType> {
    T create(T paymentType);
    Optional<T> getById(Long id);
    Optional<T> getByName(String name);
    long countByName(String name);
    void delete(T paymentType);
    void update(T paymentType);
    List<T> list();
}
