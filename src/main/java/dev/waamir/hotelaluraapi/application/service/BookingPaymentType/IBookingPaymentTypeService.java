package dev.waamir.hotelaluraapi.application.service.BookingPaymentType;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IBookingPaymentTypeService<T extends BookingPaymentType> {
        T create(T bookingPaymentType);
        List<T> listByBooking(Booking booking);
        Optional<T> getById(Long id);
        void delete(T bookingPaymentType);
        void update(T bookingPaymentType);
}
