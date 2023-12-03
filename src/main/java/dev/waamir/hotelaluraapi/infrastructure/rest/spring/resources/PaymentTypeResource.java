package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.domain.model.PaymentType;
import dev.waamir.hotelaluraapi.domain.port.IPaymentTypeRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.DuplicateRecordException;
import jakarta.validation.Valid;
import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.PaymentType.PaymentTypeRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.PaymentType.PaymentTypeResponse;

@RestController
@RequestMapping("/api/v1/paymentType")
public class PaymentTypeResource {
    
    @Autowired
    private IPaymentTypeRepository<PaymentType> paymentTypeRepository;

    @GetMapping("/name/{name}")
    public ResponseEntity<PaymentTypeResponse> fetchByName(
        @PathVariable @Valid String name
    ) {
        PaymentType paymentType = paymentTypeRepository.getByName(name).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Payment type not found.");
            }
        );
        return ResponseEntity
            .status(200)
            .body(
                new PaymentTypeResponse(paymentType)
            );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PaymentTypeResponse> fetchById(
        @PathVariable @Valid Long id
    ) {
        PaymentType paymentType = paymentTypeRepository.getById(id).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Payment type not found.");
            }
        );
        return ResponseEntity
            .status(200)
            .body(
                new PaymentTypeResponse(paymentType)
            );
    }

    @GetMapping("/list")
    public ResponseEntity<List<PaymentTypeResponse>> list() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                paymentTypeRepository.list().stream().map(PaymentTypeResponse::new).toList()
            );
    }
    
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(
        @RequestBody @Valid PaymentTypeRequest request
    ) {
        if (paymentTypeRepository.countByName(request.name()) != 0) throw new DuplicateRecordException("Payment type already exists.");
        PaymentType newPaymentType = PaymentType.builder()
            .name(request.name())
            .build();
        newPaymentType = paymentTypeRepository.create(newPaymentType);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                MessageResponse.builder()
                    .message(String.format("Payment type registered with id: %d", newPaymentType.getId()))
                    .build()
            );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> delete(
        @PathVariable @Valid Long id
    ) {
        PaymentType paymentType = paymentTypeRepository.getById(id).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Payment type not found.");
            }
        );
        paymentTypeRepository.delete(paymentType);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                MessageResponse.builder()
                    .message(String.format("Payment type with id '%s' deleted successfully.", paymentType.getId()))   
                    .build()
            );
    }

}
