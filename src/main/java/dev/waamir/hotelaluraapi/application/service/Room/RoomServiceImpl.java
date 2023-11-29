package dev.waamir.hotelaluraapi.application.service.Room;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelaluraapi.domain.model.Room;
import dev.waamir.hotelaluraapi.domain.port.IRoomRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService<Room> {

    private final IRoomRepository<Room> roomRepository;

    @Override
    public Room create(Room room) {
        return roomRepository.create(room);
    }

    @Override
    public Optional<Room> getById(Long id) {
        return roomRepository.getById(id);
    }

    @Override
    public Optional<Room> getByNumber(Long number) {
        return roomRepository.getByNumber(number);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public void update(Room room) {
        roomRepository.update(room);
    }

    @Override
    public Page<Room> list(Pageable pagination) {
        return roomRepository.list(pagination);
    }
    
}
