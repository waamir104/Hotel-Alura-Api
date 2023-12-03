package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Booking.BookingRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Booking.BookingResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Booking.BookingUpdateRequest;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingResource {
    

    @GetMapping("/list")
    public ResponseEntity<Page<BookingResponse>> list (
        @PageableDefault(size = 10) Pageable pagination
    ) {
        // TODO implement the logic
        return null;
    }

    @GetMapping("/list/{guestEmail}")
    public ResponseEntity<Page<BookingResponse>> listByGuestEmail (
        @PageableDefault(size = 10) Pageable pagination,
        @PathVariable String email
    ) {
        // TODO implement the logic
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> fetchById (
        @PathVariable long id
    ) {
        // TODO implement the logic
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register (
        @RequestBody BookingRequest requestDto
    ) {
        // TODO implement the logic
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update (
        @RequestBody BookingUpdateRequest requestDto
    ) {
        // TODO implement the logic
        return null;
    }
}
