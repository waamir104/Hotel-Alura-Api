package dev.waamir.hotelaluraapi.adapter.repository.Booking;

import dev.waamir.hotelaluraapi.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IBookingJpaRepository extends JpaRepository<Booking, Long> {
    
    @Query("SELECT b FROM Booking b WHERE b.id = :id")
    Optional<Booking> findById(Long id);
}
