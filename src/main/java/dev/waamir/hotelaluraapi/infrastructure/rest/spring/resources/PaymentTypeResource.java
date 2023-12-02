package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.validation.Valid;
import dev.waamir.hotelaluraapi.adapter.dto.resource.PaymentType.PaymentTypeDto;

@RestController
@RequestMapping("/api/v1/paymentType")
public class PaymentTypeResource {
    
    @Autowired
    private IPaymentTypeRepository<PaymentType> paymentTypeRepository;

    @GetMapping("/name/{name}")
    public ResponseEntity<PaymentTypeDto> fetchByName(
        @PathVariable @Valid String name
    ) {
        // TODO implement the logic
        return null;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PaymentTypeDto> fetchById(
        @PathVariable @Valid Long id
    ) {
        // TODO implement the logic
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<PaymentTypeDto> list() {
        // TODO implement the logic
        return null;
    }
    
    @PostMapping("/register")
    public ResponseEntity<PaymentTypeDto> register(
        @RequestBody @Valid PaymentTypeDto request
    ) {
        // TODO implement the logic
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PaymentTypeDto> delete(
        @PathVariable @Valid Long id
    ) {
        // TODO implement the logic
        return null;
    }

}
