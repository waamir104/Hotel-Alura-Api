package dev.waamir.hotelaluraapi.adapter.repository.Room;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.waamir.hotelaluraapi.domain.model.Room;

public interface IRoomJpaRepository extends JpaRepository<Room, Long>{
    
    @Query("SELECT * FROM rooms WHERE room_id = :id")
    Optional<Room> findById(Long id);
    
    @Query("SELECT * FROM rooms WHERE number = :number")
    Optional<Room> findByNumber(Long number);
}
