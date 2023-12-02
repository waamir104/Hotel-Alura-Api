package dev.waamir.hotelaluraapi.application.service.PaymentType;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;
import dev.waamir.hotelaluraapi.domain.port.IPaymentTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements IPaymentTypeService<PaymentType> {

    private final IPaymentTypeRepository<PaymentType> paymentTypeRepository;

    @Override
    public PaymentType create(PaymentType paymentType) {
        return paymentTypeRepository.create(paymentType);
    }

    @Override
    public Optional<PaymentType> getById(Long id) {
        return paymentTypeRepository.getById(id);
    }

    @Override
    public void delete(PaymentType paymentType) {
        paymentTypeRepository.delete(paymentType);
    }

    @Override
    public void update(PaymentType paymentType) {
        paymentTypeRepository.update(paymentType);
    }

    @Override
    public List<PaymentType> list() {
        return paymentTypeRepository.list();
    }

    @Override
    public Optional<PaymentType> getByName(String name) {
        return paymentTypeRepository.getByName(name);
    }

    @Override
    public long countByName(String name) {
        return paymentTypeRepository.countByName(name);
    }
    
}
