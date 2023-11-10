package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Booking;

import java.util.List;
import java.util.Optional;

public interface IBookingRepository<T extends Booking> {
    T create(T booking);
    Optional<T> getById(Long id);
    void delete(T booking);
    void update(T booking);
    List<T> list();
}
