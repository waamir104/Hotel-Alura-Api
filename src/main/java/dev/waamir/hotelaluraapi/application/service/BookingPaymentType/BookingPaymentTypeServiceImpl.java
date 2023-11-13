package dev.waamir.hotelaluraapi.application.service.BookingPaymentType;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;
import dev.waamir.hotelaluraapi.domain.port.IBookingPaymentTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingPaymentTypeServiceImpl implements IBookingPaymentTypeService<BookingPaymentType> {

    private final IBookingPaymentTypeRepository<BookingPaymentType> bookingPaymentTypeRepository;

    @Override
    public BookingPaymentType create(BookingPaymentType bookingPaymentType) {
        return bookingPaymentTypeRepository.create(bookingPaymentType);
    }

    @Override
    public List<BookingPaymentType> listByBooking(Booking booking) {
        return bookingPaymentTypeRepository.listByBooking(booking);
    }

    @Override
    public Optional<BookingPaymentType> getById(Long id) {
        return bookingPaymentTypeRepository.getById(id);
    }

    @Override
    public void delete(BookingPaymentType bookingPaymentType) {
        bookingPaymentTypeRepository.delete(bookingPaymentType);
    }

    @Override
    public void update(BookingPaymentType bookingPaymentType) {
        bookingPaymentTypeRepository.update(bookingPaymentType);
    }
    
}
