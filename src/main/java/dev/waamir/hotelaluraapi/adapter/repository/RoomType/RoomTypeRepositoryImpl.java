package dev.waamir.hotelaluraapi.adapter.repository.RoomType;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.RoomType;
import dev.waamir.hotelaluraapi.domain.port.IRoomTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoomTypeRepositoryImpl implements IRoomTypeRepository<RoomType> {

    private final IRoomTypeJpaRepository roomTypeJpaRepository;

    @Override
    public RoomType create(RoomType roomType) {
        return roomTypeJpaRepository.save(roomType);
    }

    @Override
    public Optional<RoomType> getById(Long id) {
        return roomTypeJpaRepository.findById(id);
    }

    @Override
    public void delete(RoomType roomType) {
        roomTypeJpaRepository.delete(roomType);
    }

    @Override
    public void update(RoomType roomType) {
        roomTypeJpaRepository.save(roomType);
    }

    @Override
    public List<RoomType> list() {
        return roomTypeJpaRepository.findAll();
    }

}
