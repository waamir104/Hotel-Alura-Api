package dev.waamir.hotelaluraapi.adapter.repository.Booking;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookingJpaRepository extends JpaRepository<Booking, Long> {
    
    @Query("SELECT * FROM bookings WHERE booking_id = :id")
    Optional<Booking> findById(Long id);
}
