package dev.waamir.hotelaluraapi.adapter.repository.BookingPaymentType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;

public interface IBookingPaymentTypeJpaRepository extends JpaRepository<BookingPaymentType, Long> {
    
    @Query("SELECT * FROM booking_payment_types WHERE booking_id = :booking.id")
    List<BookingPaymentType> findByBooking(Booking booking);
    
    @Query("SELECT * FROM booking_payment_types WHERE booking_payment_type_id = :id")
    Optional<BookingPaymentType> findById(Long id);
}
