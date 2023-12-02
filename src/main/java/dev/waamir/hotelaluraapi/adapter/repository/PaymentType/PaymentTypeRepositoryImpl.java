package dev.waamir.hotelaluraapi.adapter.repository.PaymentType;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;
import dev.waamir.hotelaluraapi.domain.port.IPaymentTypeRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentTypeRepositoryImpl implements IPaymentTypeRepository<PaymentType>{
    
    private final IPaymentTypeJpaRepository paymentTypeJpaRepository;

    @Override
    public PaymentType create(PaymentType paymentType) {
        return paymentTypeJpaRepository.save(paymentType);
    }

    @Override
    public Optional<PaymentType> getById(Long id) {
        return paymentTypeJpaRepository.findById(id);
    }

    @Override
    public void delete(PaymentType paymentType) {
        paymentTypeJpaRepository.delete(paymentType);
    }

    @Override
    public void update(PaymentType paymentType) {
        paymentTypeJpaRepository.save(paymentType);
    }

    @Override
    public List<PaymentType> list() {
        return paymentTypeJpaRepository.findAll();
    }

    @Override
    public Optional<PaymentType> getByName(String name) {
        return paymentTypeJpaRepository.findByName(name);
    }

    @Override
    public long countByName(String name) {
        return paymentTypeJpaRepository.countByName(name);
    }
        
}
