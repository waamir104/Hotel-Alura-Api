package dev.waamir.hotelaluraapi.application.service.Room;

import java.util.List;
import java.util.Optional;

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
    public List<Room> list() {
        return roomRepository.list();
    }
    
}
