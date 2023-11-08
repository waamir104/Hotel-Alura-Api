package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;

import java.util.List;
import java.util.Optional;

public interface IBookingPaymentTypeRepository<T extends BookingPaymentType> {
        T create(T t);
        List<T> listByBooking();
        Optional<T> getById(int id);
        void deleteById(int id);
        void update(T t);
}
