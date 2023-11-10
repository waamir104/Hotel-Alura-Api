package dev.waamir.hotelaluraapi.adapter.repository.Room;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.Room;
import dev.waamir.hotelaluraapi.domain.port.IRoomRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoomRepositoryImpl implements IRoomRepository<Room> {

    private final IRoomJpaRepository roomJpaRepository;

    @Override
    public Room create(Room room) {
        return roomJpaRepository.save(room);
    }

    @Override
    public Optional<Room> getById(Long id) {
        return roomJpaRepository.findById(id);
    }

    @Override
    public Optional<Room> getByNumber(Long number) {
        return roomJpaRepository.findByNumber(number);
    }

    @Override
    public void delete(Room room) {
        roomJpaRepository.delete(room);
    }

    @Override
    public void update(Room room) {
        roomJpaRepository.save(room);
    }

    @Override
    public List<Room> list() {
        return roomJpaRepository.findAll();
    }
    
}
