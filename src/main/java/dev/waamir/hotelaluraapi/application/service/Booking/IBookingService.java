package dev.waamir.hotelaluraapi.application.service.Booking;

import dev.waamir.hotelaluraapi.domain.model.Booking;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IBookingService<T extends Booking> {
    T create(T booking);
    Optional<T> getById(Long id);
    void delete(T booking);
    void update(T booking);
    List<T> list();
}
