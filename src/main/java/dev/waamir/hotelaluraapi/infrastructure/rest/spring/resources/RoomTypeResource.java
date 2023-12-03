package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/roomType")
public class RoomTypeResource {
    
    @Autowired
    private IRoomTypeRepository<RoomType> roomTypeRepository;

    @GetMapping("/list")
    public ResponseEntity<Page<RoomTypeResponse>> list (
        @PageableDefault(size = 5) Pageable pagination
    ) {
        // TODO implement the logic
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register (
        @RequestBody @Valid RoomTypeRegisterRequest request
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

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update (
        @RequestBody @Valid RoomTypeUpdateRequest request
    ) {
        // TODO implement the logic
        return null;
    }
}
