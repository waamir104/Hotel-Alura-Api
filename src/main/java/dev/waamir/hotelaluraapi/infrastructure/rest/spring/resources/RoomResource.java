package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

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

import dev.waamir.hotelaluraapi.adapter.dto.resource.Room.CreateRoomRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Room.RoomDto;
import dev.waamir.hotelaluraapi.domain.model.Room;
import dev.waamir.hotelaluraapi.domain.port.IRoomRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.RoomNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/room")
public class RoomResource {

    @Autowired
    private IRoomRepository<Room> roomRepository;
    
    @GetMapping("/list")
    public ResponseEntity<Page<RoomDto>> list (
        @PageableDefault(size = 10) Pageable pagination
    ) {
        return ResponseEntity.ok(roomRepository.list(pagination).map(RoomDto::new));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<RoomDto> fetchByNumber (
        @PathVariable Long number
    ) {
        return ResponseEntity.ok(
            new RoomDto(
                roomRepository.getByNumber(number).orElseThrow(() -> {
                        throw new RoomNotFoundException("");
                    }
                )
            )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (
        @RequestBody @Valid CreateRoomRequest dto
    ) {
        // TODO implement the logic for the creation of the room
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update (
        @RequestBody @Valid RoomDto dto
    ) {
        Room dbRoom = roomRepository.getByNumber(dto.number())
            .orElseThrow(() -> {
                throw new RoomNotFoundException("");
            });
        Room room = Room.builder()
            .id(dbRoom.getId())
            .number(dto.number())
            .description(dto.description())
            .roomType(dbRoom.getRoomType())
            .bookings(dbRoom.getBookings())
            .build();
        roomRepository.update(room);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("");
    }
}
