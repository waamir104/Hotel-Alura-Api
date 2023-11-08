package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Booking;

import java.util.List;
import java.util.Optional;

public interface IBookingRepository<T extends Booking> {
    T create(T t);
    Optional<T> getById(int id);
    void deleteById(int id);
    void update(T t);
    List<T> list();
}
