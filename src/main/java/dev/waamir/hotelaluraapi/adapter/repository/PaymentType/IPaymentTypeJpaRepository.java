package dev.waamir.hotelaluraapi.adapter.repository.PaymentType;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPaymentTypeJpaRepository extends JpaRepository<PaymentType, Long> {
    
    @Query("SELECT * FROM payment_types WHERE payment_type_id = :id")
    Optional<PaymentType> findById(Long id);
}
