package dev.waamir.hotelaluraapi.adapter.repository.Room;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelaluraapi.domain.model.Room;
import dev.waamir.hotelaluraapi.domain.port.IRoomRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.DuplicateRecordException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl implements IRoomRepository<Room> {

    private final IRoomJpaRepository roomJpaRepository;

    @Override
    public Room create(Room room) {
        Room roomCreated;
        try {
            roomCreated = roomJpaRepository.save(room);
        } catch (Exception e) {
            throw new DuplicateRecordException("Room is already registered");
        }
        return roomCreated;
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
