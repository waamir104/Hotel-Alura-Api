package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomRepository<T extends Room> {
    T create(T room);
    Optional<T> getById(Long id);
    Optional<T> getByNumber(Long number);
    void delete(T room);
    void update(T room);
    List<T> list();
}
