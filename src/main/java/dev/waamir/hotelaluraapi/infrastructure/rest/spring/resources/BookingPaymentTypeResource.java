package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.BookingPaymentType.BookingPaymentTypeRegisterRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.BookingPaymentType.BookingPaymentTypeResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.BookingPaymentType.BookingPaymentTypeUpdateRequest;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;
import dev.waamir.hotelaluraapi.domain.port.IBookingPaymentTypeRepository;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/bookingPaymentType")
public class BookingPaymentTypeResource {
    
    @Autowired
    private IBookingPaymentTypeRepository<BookingPaymentType> bookingPaymentTypeRepository;

    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingPaymentTypeResponse> fetchByBooking (
        @PathVariable Long bookingId
    ){
        // TODO implement the logic
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(
        @RequestBody BookingPaymentTypeRegisterRequest request
    ) {
        // TODO implement the logic
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update(
        @RequestBody BookingPaymentTypeUpdateRequest request
    ) {
        // TODO implement the logic
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> delete (
        @PathVariable @NotNull Long id
    ) {
        // TODO implement the logic
        return null;
    }
}
