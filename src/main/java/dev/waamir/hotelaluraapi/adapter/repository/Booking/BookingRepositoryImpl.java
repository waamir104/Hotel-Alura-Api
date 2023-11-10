package dev.waamir.hotelaluraapi.adapter.repository.Booking;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.port.IBookingRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingRepositoryImpl implements IBookingRepository<Booking> {

    private final IBookingJpaRepository bookingJpaRepository;

    @Override
    public Booking create(Booking booking) {
        return bookingJpaRepository.save(booking);
    }

    @Override
    public Optional<Booking> getById(Long id) {
        return bookingJpaRepository.findById(id);
    }

    @Override
    public void delete(Booking booking) {
        bookingJpaRepository.delete(booking);
    }

    @Override
    public void update(Booking booking) {
        bookingJpaRepository.save(booking);
    }

    @Override
    public List<Booking> list() {
        return bookingJpaRepository.findAll();
    }

}
