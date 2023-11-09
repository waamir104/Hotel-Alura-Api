package dev.waamir.hotelaluraapi.adapter.repository.RoomType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.waamir.hotelaluraapi.domain.model.RoomType;

public interface IRoomTypeJpaRepository extends JpaRepository<RoomType, Long>{
    
    @Query("SELECT * FROM room_types WHERE room_type_id = :id")
    Optional<RoomType> findById(Long id);
}
