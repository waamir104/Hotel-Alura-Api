package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomRepository<T extends Room> {
    T create(T t);
    Optional<T> getById(Long id);
    Optional<T> getByNumber(Long number);
    void delete(T t);
    void update(T t);
    List<T> list();
}
