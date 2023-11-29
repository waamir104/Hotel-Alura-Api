package dev.waamir.hotelaluraapi.adapter.repository.Room;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelaluraapi.domain.model.Room;
import dev.waamir.hotelaluraapi.domain.port.IRoomRepository;
import lombok.RequiredArgsConstructor;

@Repository
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
    public Page<Room> list(Pageable pagination) {
        return roomJpaRepository.findAll(pagination);
    }
    
}
