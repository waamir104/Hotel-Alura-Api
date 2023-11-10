package dev.waamir.hotelaluraapi.adapter.repository.BookingPaymentType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;

public interface IBookingPaymentTypeJpaRepository extends JpaRepository<BookingPaymentType, Long> {
    
    @Query("SELECT bpt FROM BookingPaymentType bpt WHERE bpt.booking = :booking")
    List<BookingPaymentType> findByBooking(Booking booking);
    
    @Query("SELECT bpt FROM BookingPaymentType bpt WHERE bpt.id = :id")
    Optional<BookingPaymentType> findById(Long id);
}
