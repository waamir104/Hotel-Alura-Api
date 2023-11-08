package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeRepository<T extends PaymentType> {
    T create(T t);
    Optional<T> getById(int id);
    void deleteById(int id);
    void update(T t);
    List<T> list();
}
