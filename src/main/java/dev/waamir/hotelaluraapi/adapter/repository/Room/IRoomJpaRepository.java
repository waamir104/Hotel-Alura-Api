package dev.waamir.hotelaluraapi.adapter.repository.Room;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.waamir.hotelaluraapi.domain.model.Room;

public interface IRoomJpaRepository extends JpaRepository<Room, Long>{
    
    @Query("SELECT r FROM Room r WHERE r.id = :id")
    Optional<Room> findById(Long id);
    
    @Query("SELECT r FROM Room r WHERE r.number = :number")
    Optional<Room> findByNumber(Long number);
}
