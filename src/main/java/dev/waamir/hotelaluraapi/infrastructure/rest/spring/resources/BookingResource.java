package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import dev.waamir.hotelaluraapi.domain.model.Booking;
import dev.waamir.hotelaluraapi.domain.model.Guest;
import dev.waamir.hotelaluraapi.domain.model.Room;
import dev.waamir.hotelaluraapi.domain.model.RoomType;
import dev.waamir.hotelaluraapi.domain.port.IBookingRepository;
import dev.waamir.hotelaluraapi.domain.port.IGuestRepository;
import dev.waamir.hotelaluraapi.domain.port.IRoomRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.GuestNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.RoomNotFoundException;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingResource {

    @Autowired
    private IBookingRepository<Booking> bookingRepository;
    @Autowired
    private IGuestRepository<Guest> guestRepository;
    @Autowired
    private IRoomRepository<Room> roomRepository;

    @GetMapping("/list")
    public ResponseEntity<Page<BookingResponse>> list (
        @PageableDefault(size = 10) Pageable pagination
    ) {
        return ResponseEntity
            .status(200)
            .body(
                bookingRepository.list(pagination).map(BookingResponse::new)
            );
    }

    @GetMapping("/list/{guestEmail}")
    public ResponseEntity<Page<BookingResponse>> listByGuestEmail (
        @PageableDefault(size = 10) Pageable pagination,
        @PathVariable String email
    ) {
        Guest guest = guestRepository.getByEmail(email).orElseThrow(
            () -> {
                throw new GuestNotFoundException("");
            }
        );
        return ResponseEntity
            .status(200)
            .body(
                bookingRepository.listByGuestEmail(guest.getEmail(), pagination).map(BookingResponse::new)
            );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> fetchById (
        @PathVariable long id
    ) {
        Booking booking = bookingRepository.getById(id).orElseThrow(
            () -> {
                throw new ApiNotFoundException("");
            }
        );
        return ResponseEntity
            .status(200)
            .body(
                new BookingResponse(booking)
            );
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register (
        @RequestBody BookingRequest requestDto
    ) {
        Guest guest = guestRepository.getByEmail(requestDto.guestEmail()).orElseThrow(
            () -> {
                throw new GuestNotFoundException("");
            }
        );
        Room room = roomRepository.getByNumber(requestDto.roomNumber()).orElseThrow(
            () -> {
                throw new RoomNotFoundException("");
            }
        );
        RoomType roomType = room.getRoomType();
        long days = ChronoUnit.DAYS.between(requestDto.checkIn(), requestDto.checkOut());
        double totalPrice = roomType.getDailyPrice() * days;
        Booking newBooking = Booking.builder()
            .checkIn(requestDto.checkIn())
            .checkOut(requestDto.checkOut())
            .guest(guest)
            .room(room)
            .totalPrice(totalPrice)
            .build();
        newBooking = bookingRepository.create(newBooking);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                MessageResponse.builder()
                    .message(String.format("Booking created with id: %d", newBooking.getId()))
                    .build()
            );
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update (
        @RequestBody BookingUpdateRequest requestDto
    ) {
        Booking booking = bookingRepository.getById(requestDto.id()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("");
            }
        );
        booking.setCheckIn(requestDto.checkIn());
        booking.setCheckOut(requestDto.checkOut());
        bookingRepository.update(booking);
        return ResponseEntity
            .status(200)
            .body(
                MessageResponse.builder()
                    .message("Booking updated succesfully")
                    .build()
            );
    }
}
