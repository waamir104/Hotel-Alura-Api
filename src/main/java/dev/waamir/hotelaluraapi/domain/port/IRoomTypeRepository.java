package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.RoomType;

import java.util.List;
import java.util.Optional;

public interface IRoomTypeRepository<T extends RoomType> {
    T create(T t);
    Optional<T> getById(Long id);
    void delete(T t);
    void update(T t);
    List<T> list();
}
