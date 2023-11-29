package dev.waamir.hotelaluraapi.application.service.RoomType;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.RoomType;
import dev.waamir.hotelaluraapi.domain.port.IRoomTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoomTypeServiceImpl implements IRoomTypeService<RoomType> {

    private final IRoomTypeRepository<RoomType> roomTypeRepository;

    @Override
    public RoomType create(RoomType roomType) {
        return roomTypeRepository.create(roomType);
    }

    @Override
    public Optional<RoomType> getById(Long id) {
        return roomTypeRepository.getById(id);
    }

    @Override
    public void delete(RoomType roomType) {
        roomTypeRepository.delete(roomType);
    }

    @Override
    public void update(RoomType roomType) {
        roomTypeRepository.update(roomType);
    }

    @Override
    public List<RoomType> list() {
        return roomTypeRepository.list();
    }

    @Override
    public Optional<RoomType> getByName(String name) {
        return roomTypeRepository.getByName(name);
    }

    @Override
    public Long countByName(String name) {
        return roomTypeRepository.countByName(name);
    }
    
}
