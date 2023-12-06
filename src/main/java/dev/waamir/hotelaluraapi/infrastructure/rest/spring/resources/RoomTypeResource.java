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
import dev.waamir.hotelaluraapi.adapter.dto.resource.RoomType.RoomTypeRegisterRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.RoomType.RoomTypeResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.RoomType.RoomTypeUpdateRequest;
import dev.waamir.hotelaluraapi.domain.model.RoomType;
import dev.waamir.hotelaluraapi.domain.port.IRoomTypeRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.DuplicateRecordException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.GenericException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/roomType")
public class RoomTypeResource {
    
    @Autowired
    private IRoomTypeRepository<RoomType> roomTypeRepository;

    @GetMapping("/list")
    public ResponseEntity<List<RoomTypeResponse>> list (
    ) {
        return ResponseEntity
            .status(200)
            .body(
                roomTypeRepository.list().stream().map(RoomTypeResponse::new).toList()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register (
        @RequestBody @Valid RoomTypeRegisterRequest request
    ) {
        if (roomTypeRepository.countByName(request.name()) != 0) throw new DuplicateRecordException("Room Type already exists.");
        RoomType newRoomType = RoomType.builder()
            .name(request.name())
            .description(request.description())
            .dailyPrice(request.dailyPrice())
            .build();
        newRoomType = roomTypeRepository.create(newRoomType);
        return ResponseEntity
            .status(201)
            .body(
                MessageResponse.builder()
                    .message(String.format("Room Type registered with id: %d", newRoomType.getId()))
                    .build()
            );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> delete (
        @PathVariable @NotNull Long id
    ) {
        RoomType roomType = roomTypeRepository.getById(id).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Room Type not found.");
            }
        );
        if (!roomType.getRooms().isEmpty()) throw new GenericException("Cannot delete Room Type.", HttpStatus.CONFLICT, null);
        roomTypeRepository.delete(roomType);
        return ResponseEntity
            .status(200)
            .body(
                MessageResponse.builder()
                    .message("Room Type deleted successfully")
                    .build()
            );
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update (
        @RequestBody @Valid RoomTypeUpdateRequest request
    ) {
        RoomType roomTypeToUpdate = roomTypeRepository.getById(request.id()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Room Type not found.");
            }
        );
        roomTypeToUpdate.setName(request.name());
        roomTypeToUpdate.setDescription(request.description());
        roomTypeToUpdate.setDailyPrice(request.dailyPrice());
        roomTypeRepository.update(roomTypeToUpdate);
        return ResponseEntity
            .status(200)
            .body(
                MessageResponse.builder()
                    .message("Room Type updated successfully")
                    .build()
            );
    }
}
