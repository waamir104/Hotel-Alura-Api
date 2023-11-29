package dev.waamir.hotelaluraapi.adapter.repository.BookingPaymentType;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;
import dev.waamir.hotelaluraapi.domain.port.IBookingPaymentTypeRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookingPaymentTypeRepositoryImpl implements IBookingPaymentTypeRepository<BookingPaymentType> {
    
    private final IBookingPaymentTypeJpaRepository bookingPaymentTypeJpaRepository;
    
    @Override
    public BookingPaymentType create(BookingPaymentType bookingPaymentType) {
        return bookingPaymentTypeJpaRepository.save(bookingPaymentType);
    }

    @Override
    public List<BookingPaymentType> listByBooking(Booking booking) {
        return bookingPaymentTypeJpaRepository.findByBooking(booking);
    }

    @Override
    public Optional<BookingPaymentType> getById(Long id) {
        return bookingPaymentTypeJpaRepository.findById(id);
    }

    @Override
    public void delete(BookingPaymentType bookingPaymentType) {
        bookingPaymentTypeJpaRepository.delete(bookingPaymentType);
    }

    @Override
    public void update(BookingPaymentType bookingPaymentType) {
        bookingPaymentTypeJpaRepository.save(bookingPaymentType);
    }
    
}
