package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;

import java.util.List;
import java.util.Optional;

public interface IBookingPaymentTypeRepository<T extends BookingPaymentType> {
        T create(T t);
        List<T> listByBooking(Booking booking);
        Optional<T> getById(Long id);
        void delete(T t);
        void update(T t);
}
