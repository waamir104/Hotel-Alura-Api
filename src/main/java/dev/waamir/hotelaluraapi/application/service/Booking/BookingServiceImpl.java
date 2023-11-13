package dev.waamir.hotelaluraapi.application.service.Booking;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.port.IBookingRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService<Booking> {

    private final IBookingRepository<Booking> bookingRepository;

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.create(booking);
    }

    @Override
    public Optional<Booking> getById(Long id) {
        return bookingRepository.getById(id);
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public void update(Booking booking) {
        bookingRepository.update(booking);
    }

    @Override
    public List<Booking> list() {
        return bookingRepository.list();
    }
    
}
