package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.BookingPaymentType;
import dev.waamir.hotelaluraapi.domain.model.PaymentType;
import dev.waamir.hotelaluraapi.domain.port.IBookingPaymentTypeRepository;
import dev.waamir.hotelaluraapi.domain.port.IBookingRepository;
import dev.waamir.hotelaluraapi.domain.port.IPaymentTypeRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.GenericException;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/bookingPaymentType")
public class BookingPaymentTypeResource {
    
    @Autowired
    private IBookingPaymentTypeRepository<BookingPaymentType> bookingPaymentTypeRepository;
    @Autowired
    private IBookingRepository<Booking> bookingRepository;
    @Autowired
    private IPaymentTypeRepository<PaymentType> paymentTypeRepository;

    @GetMapping("/booking/{id}")
    public ResponseEntity<List<BookingPaymentTypeResponse>> fetchByBooking (
        @PathVariable Long bookingId
    ){
        Booking booking = bookingRepository.getById(bookingId).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Booking not found.");
            }
        );
        List<BookingPaymentType> list = bookingPaymentTypeRepository.listByBooking(booking);
        return ResponseEntity
            .status(200)
            .body(
                list.stream().map(BookingPaymentTypeResponse::new).toList()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(
        @RequestBody BookingPaymentTypeRegisterRequest request
    ) {
        Booking booking = bookingRepository.getById(request.bookingId()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Booking not found.");
            }
        );
        PaymentType paymentType = paymentTypeRepository.getById(request.paymentTypeId()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Payment type not found.");
            }
        );
        double totalPaymentPercentage = booking.getBookingPaymentTypes().stream()
            .mapToDouble(BookingPaymentType::getPaymentPercentage).sum();
        if (request.paymentPercentage() > (100 - totalPaymentPercentage)) throw new GenericException("The payment percentage exceeds the limit.", HttpStatus.BAD_REQUEST);
        BookingPaymentType newBookingPaymentType = BookingPaymentType.builder()
            .paymentPercentage(request.paymentPercentage())
            .booking(booking)
            .paymentType(paymentType)
            .build();
        newBookingPaymentType = bookingPaymentTypeRepository.create(newBookingPaymentType);
        return ResponseEntity
            .status(201)
            .body(
                MessageResponse.builder()
                    .message(String.format("Booking Payment Type registered with id: ", newBookingPaymentType.getId()))
                    .build()
            );
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update(
        @RequestBody BookingPaymentTypeUpdateRequest request
    ) {
        BookingPaymentType bookingPaymentTypeToUpdate = bookingPaymentTypeRepository.getById(request.id()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Booking Payment Type not found.");
            }
        );
        PaymentType paymentType = paymentTypeRepository.getById(request.paymentTypeId()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Payment Type not found.");
            }
        );
        Booking booking = bookingPaymentTypeToUpdate.getBooking();
        double totalPaymentPercentage = booking.getBookingPaymentTypes().stream()
            .filter(bookingPaymentType -> !(bookingPaymentType.getId() == request.id()))
            .mapToDouble(BookingPaymentType::getPaymentPercentage)
            .sum();
        totalPaymentPercentage += request.paymentPercentage();
        if (totalPaymentPercentage > 100) throw new GenericException("The payment percentage exceeds the limit.", HttpStatus.BAD_REQUEST);
        bookingPaymentTypeToUpdate.setPaymentPercentage(request.paymentPercentage());
        bookingPaymentTypeToUpdate.setPaymentType(paymentType);
        bookingPaymentTypeRepository.update(bookingPaymentTypeToUpdate);
        return ResponseEntity
            .status(200)
            .body(
                MessageResponse.builder()
                    .message("Booking Payment Type updated successfully")
                    .build()
            );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> delete (
        @PathVariable @NotNull Long id
    ) {
        BookingPaymentType bookingPaymentType = bookingPaymentTypeRepository.getById(id).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Booking Payment Type not found.");
            }
        );
        bookingPaymentTypeRepository.delete(bookingPaymentType);
        return ResponseEntity
            .status(200)
            .body(
                MessageResponse.builder()
                    .message("Booking Payment Type deleted successfully")
                    .build()
            );
    }
}
