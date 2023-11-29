package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Booking;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookingRepository<T extends Booking> {
    T create(T booking);
    Optional<T> getById(Long id);
    void delete(T booking);
    void update(T booking);
    Page<T> list(Pageable pagination);
}
