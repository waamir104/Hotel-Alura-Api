package dev.waamir.hotelaluraapi.adapter.repository.Guest;

import dev.waamir.hotelaluraapi.domain.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IGuestJpaRepository extends JpaRepository<Guest, Long> {
    @Query("SELECT g FROM Guest g WHERE g.email = :email")
    Optional<Guest> findByEmail(String email);
    @Query("SELECT g FROM Guest g WHERE g.id = :id")
    Optional<Guest> findById(Long id);
    @Query ("SELECT g FROM Guest g WHERE g.idNumber = :idNumber")
    Optional<Guest> findByIdNumber(Long idNumber);
    @Query("SELECT COUNT(g) FROM Guest g WHERE g.email = :email")
    long countByEmail(String email);
}
