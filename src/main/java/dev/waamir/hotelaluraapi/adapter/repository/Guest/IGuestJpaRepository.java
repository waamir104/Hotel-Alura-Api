package dev.waamir.hotelaluraapi.adapter.repository.Guest;

import dev.waamir.hotelaluraapi.domain.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGuestJpaRepository extends JpaRepository<Guest, Long> {
    @Query("SELECT * FROM guests WHERE email = :email")
    Optional<Guest> findByEmail(String email);
    @Query("SELECT * FROM guests WHERE id = :id")
    Optional<Guest> findById(Long id);
}
