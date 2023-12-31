package dev.waamir.hotelaluraapi.adapter.repository.Booking;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.port.IBookingRepository;
import lombok.RequiredArgsConstructor;

@Repository
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
    public Page<Booking> list(Pageable pagination) {
        return bookingJpaRepository.findAll(pagination);
    }

    @Override
    public Page<Booking> listByGuestEmail(String email, Pageable pagination) {
        return bookingJpaRepository.findByGuestEmail(email, pagination);
    }

}
