package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.RoomType;

import java.util.List;
import java.util.Optional;

public interface IRoomTypeRepository<T extends RoomType> {
    T create(T roomType);
    Optional<T> getById(Long id);
    Optional<T> getByName(String name);
    void delete(T roomType);
    void update(T roomType);
    List<T> list();
}
