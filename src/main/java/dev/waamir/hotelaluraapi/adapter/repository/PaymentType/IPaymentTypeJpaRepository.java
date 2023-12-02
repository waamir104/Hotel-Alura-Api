package dev.waamir.hotelaluraapi.adapter.repository.PaymentType;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPaymentTypeJpaRepository extends JpaRepository<PaymentType, Long> {
    
    @Query("SELECT pt FROM PaymentType pt WHERE pt.id = :id")
    Optional<PaymentType> findById(Long id);

    @Query("SELECT pt FROM PaymentType pt WHERE pt.name = :name")
    Optional<PaymentType> findByName(String name);

    @Query("SELECT COUNT(pt) FROM PaymentType pt WHERE pt.name = :name")
    long countByName(String name);
}
